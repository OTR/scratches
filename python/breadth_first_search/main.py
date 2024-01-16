"""
Script to follow Web URL's starting from root page of given website.
"""
import argparse
import gzip
import logging
from typing import Union
from urllib import request
from urllib.error import HTTPError, URLError
from urllib.parse import urlparse

import config

START_HOST = config.START_HOST
SEED_FILE = config.SEED_FILE
PICKLE_FILE = config.PICKLE_FILE
DATABASE_FILE = config.DATABASE_FILE

HREF_REGEXP = config.HREF_REGEXP
USER_AGENT = config.USER_AGENT
FILTERS_TO_APPLY = config.FILTERS_TO_APPLY

LOG_FILE = config.LOG_FILE
LOG_LEVEL = config.LOG_LEVEL
LOG_MESSAGE_FORMAT = config.LOG_MESSAGE_FORMAT
LOG_DATE_FORMAT = config.LOG_DATE_FORMAT


class Crawler(object):
    """To keep variables such as `logger`, `opener` in local scope."""

    def __init__(self,
                 log_level: int = LOG_LEVEL,
                 user_agent: str = USER_AGENT):
        """
        Prepare logger, opener objects etc.
        self.seen - to keep hosts we already visited
        self.to_see - to keep hosts we are going to visit

        :param log_level: well known logging levels from logging module
            set DEBUG by default
        :param user_agent: will be used as `User-Agent` header in HTTP requests
        """
        self.logger = self.prepare_logger(log_level)  # TODO: class attribute
        self.user_agent = user_agent
        self.opener = request.build_opener()
        self.opener.addheaders = [
            ("User-Agent", self.user_agent),
            ("Content-Type", "text/html; charset=utf-8"),
            ("Accept-Charset", "utf-8")
        ]

        self.seen = set()
        self.to_see = set()

    @staticmethod
    def prepare_logger(log_level: int) -> logging.Logger:
        """
        Create and tune up a logger object.

        :param log_level: well known logging levels from logging module
        :return: built up Logger object
        """
        logger = logging.getLogger("crawler")
        file_handler = logging.FileHandler(LOG_FILE)
        file_handler.setFormatter(
            logging.Formatter(fmt=LOG_MESSAGE_FORMAT,
                              datefmt=LOG_DATE_FORMAT)
        )
        logger.addHandler(file_handler)
        logger.setLevel(log_level)

        return logger

    def get_html_source(self, url: str) -> str:
        """
        Handler for requesting a web page.
        Tries to recognize `content-encoding` and `content-charset` headers.

        :param url: a URL to go to
        :return: source HTML code or None
        """
        try:
            response = self.opener.open(url)
            log_msg = "Response code for url `{url}` is: `{code}`".format(
                url=url,
                code=response.code
            )
            self.logger.debug(log_msg)
            charset = response.headers.get_content_charset()
            encoding = response.headers.get("content-encoding", None)
            source = response.read()
            if encoding:
                source = gzip.decompress(source)
            if not charset:
                charset = "UTF-8"
            decoded_html = source.decode(charset)
            if not decoded_html:
                log_msg = "Response body for url `{url}` is empty.".format(
                    url=url
                )
                self.logger.info(log_msg)
                raise UserWarning(log_msg)

            return decoded_html
        except HTTPError as err:
            status_code = err.status
            log_msg = ""
            if 400 <= status_code < 500:
                if (status_code == 403
                        and err.headers["Server"] == "cloudflare"):
                    log_msg = "`{url}` is protected by cloudflare, use cfscrape"
                else:
                    log_msg = "Is given URL valid? {url}"
            self.logger.info(log_msg.format(url=url))
            raise err
        except URLError as err:
            if err.args[0].strerror == "getaddrinfo failed":
                log_msg = "Unknown domain {url}".format(url=url)
            else:
                log_msg = err
            self.logger.info(log_msg)
            raise err
        except Exception as err:
            self.logger.info(err)
            raise err

    @staticmethod
    def check_for_redirect(url: str) -> Union[str, None]:
        """
        Try to request given url to find out weather it causes redirect
        or not. Return `redirected_url` if while requesting `url` redirect
        has happened. Otherwise return None ?

        :param url: HTTP URL to check for redirect
        :return: redirected_url or None
        """
        return None  # TODO: Implement me

    def find_all_hrefs(self, source: str) -> dict:
        """
        Extract URL's from `href` attribute of all anchor HTML tags from given
        HTML chunk using regular expression. Divide URL's that lead to another
        host and those that lead within original host.

        :param source: source HTML code to get anchor tags from
        :return: a dict of found URL's with keys `internal` and `external`
        """
        internal_urls = set()
        external_urls = set()

        found_hrefs = HREF_REGEXP.findall(source)
        if found_hrefs:
            for href in found_hrefs:
                # Skip javascript in `href` attribute
                if href.startswith("#") or href.startswith("javascript"):
                    continue
                elif (href.startswith("http://") or href.startswith("https://")
                      or href.startswith("//")):
                    external_urls.add(href)
                # Skip urls to other protocols
                elif href.startswith("tel:") or href.startswith("mailto:"):
                    continue
                elif (href.startswith("\"") or href.startswith("'") or
                      href.startswith("{")):
                    log_msg = "Unexpected href `{href}`".format(href=href)
                    self.logger.info(log_msg)
                else:
                    # TODO: Provide an original host to compare with
                    redirected = self.check_for_redirect(href)
                    if redirected:
                        external_urls.add(redirected)
                    else:
                        internal_urls.add(href)

        return {"internal": internal_urls, "external": external_urls}

    @staticmethod
    def print_result_to_console(urls: dict) -> None:
        """
        Pretty print to console.

        :param urls: a dict of found urls with keys `internal` and `external`
        """
        lines = []

        lines.append(
            "Found {in_num} internal urls:".format(
                in_num=len(urls["internal"])
            )
        )
        for in_url in urls["internal"]:
            lines.append("\t{in_url}".format(in_url=in_url))
        lines.append(
            "Found {ex_num} external urls:".format(
                ex_num=len(urls["external"])
            )
        )
        for ex_url in urls["external"]:
            lines.append("\t{ex_url}".format(ex_url=ex_url))

        multiline = "\n".join(lines)
        print(multiline)

    @staticmethod
    def get_host_from_url(url: str) -> str:
        """
        Parse url, get `netloc` parameter, trim it to second level domain.

        :param url: a HTTP URL to parse
        :return: second level domain
        """
        try:
            full_host = urlparse(url).netloc
            # Collect only 2 first levels of domain structure
            # FIXME: Possible IndexError
            host = ".".join(full_host.split(".")[-2:])
            if host:
                return host
            else:
                log_msg = "Cant get host from url `{url}`".format(url=url)
                raise UserWarning(log_msg)
        except Exception as err:
            raise err

    def collect_external_urls(self, host: str) -> None:
        """
        Collect all URL's.

        :param host: a host which root page `/` will be parsed.
        """
        root_page = "http://{host}/".format(host=host)
        print(f"visiting {root_page}")
        source = self.get_html_source(root_page)
        self.seen.add(host)
        urls = self.find_all_hrefs(source)
        if urls["external"]:
            for ex_url in urls["external"]:
                next_host = self.get_host_from_url(ex_url)
                # TODO: pass all the filters
                if all(self.apply_filters(next_host)):
                    if next_host not in self.seen:
                        self.to_see.add(next_host)
                else:
                    log_msg = "`{next_host}` hasn't passed all tests.".format(
                        next_host=next_host
                    )
                    self.logger.debug(log_msg)

    @staticmethod
    def apply_filters(host: str) -> list[bool]:
        """
        Map given host on all filters registered in global variable
        `FILTERS_TO_APPLY` in `CONFIG`. Each filter returns True if passed,
        otherwise returns False.

        :param host: a host to pass through all filters
        :return: List of booleans, if all filters has passed returns all True's
        """
        passed_filters = []
        if FILTERS_TO_APPLY:
            for host_filter in FILTERS_TO_APPLY:
                passed_filters.append(host_filter(host))
            return passed_filters
        else:
            return [True]

    def main(self, args: argparse.Namespace) -> None:
        """
        Walk through web pages collecting external urls

        :param args: namespace object containing arguments provided with
            console.
        """
        if args.host:
            self.start_from_host(args.host)
        elif args.seed:
            self.start_from_seed(args.seed)
        elif args.pickle:
            self.start_from_pickle(args.pickle)
        elif args.database:
            self.start_from_database(args.database)
        else:
            self.start_from_host(START_HOST)

        while self.to_see:
            next_host = self.to_see.pop()  # FIXME: set object is not ordered
            self.collect_external_urls(next_host)

    def start_from_host(self, host_to_start: str) -> None:
        """Add given host to `visiting queue`"""
        print(f"Starting in `host` mode with given host: {host_to_start}")
        self.to_see.add(host_to_start)

    def start_from_seed(self, seed_file) -> None:
        """"""
        raise NotImplementedError

    def start_from_pickle(self, pickle_file) -> None:
        """"""
        raise NotImplementedError

    def start_from_database(self, database_file) -> None:
        """"""
        raise NotImplementedError


def parse_arguments() -> argparse.Namespace:
    """
    Parse arguments provided via console, set defaults if needed and pass
    them on to main method. There are three groups of arguments:
    mode - `run once` and print out results or `run infinitely` following URL's.
        by default if runs infinitely. TODO: implement `run_once` mode,
        from previous versions of the script.

    load_group - mutually exclusive group of options to tell the script the way
        you want to load predefined working state variables `to_see` and `seen`

    log_level - debug, verbose, quiet and others

    :return: parsed arguments, each one could be access by calling a method
        with corresponding name
    """
    parser = argparse.ArgumentParser()
    load_group = parser.add_mutually_exclusive_group()
    load_group.add_argument("--host",
                            help="a host to start from",
                            type=str)
    load_group.add_argument("--seed",
                            help="a path to a plain text file with hosts to "
                                 "start from",
                            type=str)
    load_group.add_argument("--pickle",
                            help="a path to pickle file to save/load working "
                                 "state",
                            type=str)
    load_group.add_argument("--database",
                            help="a path to database as file to save/load "
                                 "working state",
                            type=str)
    args = parser.parse_args()

    return args


if __name__ == "__main__":
    named_args = parse_arguments()
    crawler = Crawler()
    crawler.main(named_args)
