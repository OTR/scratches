"""
Gather information about existing books on Amazon by given search query.

Each book entry is within that tag:
<span cel_widget_id="MAIN-SEARCH_RESULTS-7" .../>

TODO:
    find fields containing Title, Author, Price for paperback,
    add argparse
    type hinting

"""

import logging
import sys
from typing import List, Union
from urllib.request import build_opener
from urllib.parse import urlencode, urlunparse, quote_plus

try:
    from lxml import html
except ImportError as err:
    print("Could not run this script without lxml library.\nUse `$ pip \
          install lxml`")
    raise err


class AmazonParser(object):  # TODO: inherit from urllib.request.OpenerDirector
    """Hold methods to parse `amazon.com`."""

    def __init__(self, log_stream="stdout", log_level="warning"):
        """
        Build simple http opener with User-Agent header.

        to satisfy Amazon web server. Also, create logger for dev purpose.

        :param: log_stream: where to log
        :param: log_level: what to log
        """
        self.logger = logging.getLogger("amazon_parser")
        if log_stream == "stdout":
            stdout_handler = logging.StreamHandler(sys.stderr)
            self.logger.addHandler(stdout_handler)
        if log_level == "debug":
            self.logger.setLevel(logging.DEBUG)

        # Build own HTTP opener
        self.opener = build_opener()
        user_agent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64)"
        headers = [("User-Agent", user_agent)]
        self.opener.addheaders = headers

    def create_url(self, keyword: str = "", page: int = 1) -> str:
        """
        Wrap around urllib.parse.urlunparse function, following given scheme.

        <scheme>://<netloc>/<path>;<params>?<query>#<fragment>
        :param keyword: to search for
        :param page: to start from
        :return: built URL as string
        """
        scheme = "https"
        netloc = "www.amazon.com"
        path = "s"
        urlparams = ""
        query_keys = {
            "k": keyword,
            "i": "stripbooks-intl-ship",
            "page": page
        }
        fragment = ""
        query = urlencode(query_keys)
        components = (scheme, netloc, path, urlparams, query, fragment)
        url_pattern = urlunparse(components)

        return url_pattern

    def get_search_page(self, keyword: str = "", page: int = 1):
        """
        Request search results from Amazon by a keyword.

        :return: http.client.HTTPResponse or None
        """
        quoted_keyword = quote_plus(keyword)
        amazon_url = self.create_url(keyword=quoted_keyword, page=page)
        self.logger.debug(amazon_url)
        response = self.opener.open(amazon_url)
        if response.status > 200:  # < 300 ?
            self.logger.debug("status code is not OK")
            return None

        return response

    def parse_books_on_page(self, response):
        """
        Parse http response by XPath equations.

        :param response: http.client.HTTPResponse
        :return: found book entities
        """
        tree = html.parse(response)
        if not isinstance(tree.getroot(), html.HtmlElement):
            self.logger.debug("Couldnt parse html tree from given response")
            # raise own error ?
            return None

        # Find chunks of html tree that contains information about one book
        book_items = tree.xpath(
            '//span[contains(@cel_widget_id, "MAIN-SEARCH_RESULTS-")]',
        )
        if book_items:
            self.logger.debug(
                "Found {num} books on a page".format(num=len(book_items)),
            )
        else:
            self.logger.debug("Couldnt find book entity")
            return None

        books_on_page = []

        for book_item in book_items:
            # Note that dot means relative path
            img_tag = book_item.xpath(".//img[@class='s-image']")
            # self.logger.debug(len(img_tag))  # FIXME: delete me
            self.logger.debug(img_tag[0].attrib["src"])
            self.logger.debug(img_tag[0].attrib["alt"])
            self.logger.debug("_" * 80)

            book = {}
            book["image_src"] = img_tag[0].attrib["src"]
            book["image_name"] = img_tag[0].attrib["alt"]
            books_on_page.append(book)

        return books_on_page

    def main(self, keyword: str = "", page: int = 1) -> Union[List, None]:
        """
        Get books from amazon search page by given keyword.

        :param keyword: to search for
        :param page: to start on
        :return: List of dictionaries, each element describes book meta info
        """
        response = self.get_search_page(keyword=keyword, page=page)
        if response is None:
            return None
        books_on_page = self.parse_books_on_page(response)

        return books_on_page


if __name__ == "__main__":
    Parser = AmazonParser(log_level="debug")
    parsed_books = Parser.main(keyword="python", page=1)
