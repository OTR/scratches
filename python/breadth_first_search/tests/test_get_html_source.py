"""
Tests to help with refactoring.

`Constants`:
    OLD_USER_AGENT - used to test twitter's response on providing an User-Agent
    header of an old browser
"""
import unittest
from urllib import request
from urllib.error import HTTPError

import config
import main

BASE_DIR = config.BASE_DIR
USER_AGENT = config.USER_AGENT
OLD_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0)" \
                 " Gecko/20100101 Firefox/29.0"


class TwitterWithOldUserAgentTestCase(unittest.TestCase):
    """
    When providing an old `User-Agent` of an old browser twitter redirects to
    mobile version. When using a new one redirects to https://twitter.com/

    The problem is that `urlopen` doesn't handle redirects
    `OpenerDirector` uses `HTTPRedirectHandler` but still raises 400.
    You only could figure out that you were redirected by looking at attribute
    `req.redirect_dict`
    {'https://mobile.twitter.com/': 1}

    `URLOpener` raises 302
    `FancyURLOpener` not raises any exceptions but returns code 400
    You only could figure out that you were redirected by looking at attribute
    err.headers['Location'] == 'https://mobile.twitter.com for 2 last cases.
    """

    @classmethod
    def setUpClass(cls) -> None:
        """Prepare `Crawler` object."""
        cls.crawler = main.Crawler(user_agent=OLD_USER_AGENT)
        cls.url = "http://twitter.com/"

    def test_cant_handle_302_error(self):
        """
        Assert raises HTTPError with status code 400 when using urlopen
        method or OpenerDirector.
        """
        with self.assertRaises(HTTPError) as context_manager:
            response = self.crawler.get_html_source(self.url)

        err = context_manager.exception
        expected = 400
        actual = err.status

        self.assertEqual(actual, expected)

    def test_get_implicitly_redirected(self):
        """Assert that we are implicitly redirected to another host."""
        with self.assertRaises(HTTPError) as context_manager:
            response = self.crawler.get_html_source(self.url)

        err = context_manager.exception
        expected = "https://mobile.twitter.com/"
        actual = err.url

        self.assertEqual(expected, actual)


class TwitterWithModernUserAgentTestCase(unittest.TestCase):
    """
    Test twitter response with user agent of a modern web browser
    as Firefox 94.0
    """

    @classmethod
    def setUpClass(cls) -> None:
        """Prepare `Crawler` object."""
        cls.crawler = main.Crawler(user_agent=OLD_USER_AGENT)
        cls.url = "http://twitter.com/"

    def test_refactoring_with_redirect_handler(self):
        """
        Test if adding `HTTPRedirectHandler` helps handle 302 code.
        Used for refactoring only.
        """
        opener = request.build_opener(request.HTTPRedirectHandler)
        opener.addheaders = [("User-Agent", USER_AGENT)]

        resp = opener.open(self.url)

        expected = 200
        actual = resp.code

        self.assertEqual(expected, actual)

    def test_refactoring_without_redirect_handler(self):
        """
        Test if absence of `HTTPRedirectHandler` causes 400 code.
        Used for refactoring only. Looks like it doesn't matter.
        """
        opener = request.build_opener()
        opener.addheaders = [("User-Agent", USER_AGENT)]

        resp = opener.open(self.url)

        expected = 400
        actual = resp.code

        self.assertNotEqual(expected, actual)
