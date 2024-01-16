"""
Tests to ensure fixing Issues
"""
import gzip
import unittest

import config
import main


class UnicodeDecodeErrorTestCase(unittest.TestCase):
    """
    Fix Issue #2
    https://github.com/OTR/breadth_first_search/issues/2
    """

    def test_vk_com_charset_negative(self):
        """
        Test getting content in cp1251 charset from vk.com
        when Content-Type header is not set
        Bad test practice: most likely it sends with cp1251 depend on my IP
        and User-Agent, could not be reproduced on other machines.
        """
        crawler = main.Crawler()
        crawler.opener.addheaders = [
            ("User-Agent", config.USER_AGENT),
        ]

        source = crawler.opener.open("http://vk.com/").read()
        with self.assertRaises(UnicodeDecodeError) as context_manager:
            source.decode("UTF-8")

        err = context_manager.exception
        actual = err.reason
        expected = "invalid continuation byte"

        self.assertEqual(actual, expected)

        resp_as_bytes = err.object
        resp_as_str = resp_as_bytes.decode("cp1251")

        self.assertIsInstance(resp_as_str, str)

    def test_vk_com_charset_utf8_negative(self):
        """
        Looks like setting up Content-Type and Accept-Charset doesnt work
        with vk.com
        """
        crawler = main.Crawler()
        crawler.opener.addheaders = [
            ("User-Agent", config.USER_AGENT),
            ("Content-Type", "text/html; charset=utf-8"),
            ("Accept-Charset", "utf-8")
        ]
        source = crawler.opener.open("http://vk.com/").read()

        with self.assertRaises(UnicodeDecodeError) as context_manager:
            source.decode("UTF-8")

    def test_vk_com_get_encoding_from_headers_positive(self):
        """
        Try to decode with encoding set in response headers
        """
        crawler = main.Crawler()
        # resp = crawler.opener.open("http://vk.com/")
        # decoded_resp = resp.read().decode(resp.headers.get_content_charset())
        decoded_resp = crawler.get_html_source("http://vk.com/")

        self.assertIsInstance(decoded_resp, str)

    def test_thinking_with_google_negative(self):
        """
        Fail when trying to decode gzip'ed content
        """
        crawler = main.Crawler()
        resp = crawler.opener.open("http://thinkwithgoogle.com/")

        with self.assertRaises(UnicodeDecodeError) as context_manager:
            decoded_resp = resp.read().decode(
                resp.headers.get_content_charset()
            )

        actual = context_manager.exception.reason
        expected = "invalid start byte"

        self.assertEqual(actual, expected)

    def test_thinking_with_google_positive(self):
        """
        Decompress before decoding if content-encoding set to gzip
        """
        crawler = main.Crawler()
        response = crawler.opener.open("http://thinkwithgoogle.com/")

        charset = response.headers.get_content_charset()
        encoding = response.headers.get("content-encoding", None)
        source = response.read()
        if encoding:
            source = gzip.decompress(source)
        if not charset:
            charset = "UTF-8"
        decoded_html = source.decode(charset)

        self.assertIsInstance(decoded_html, str)
