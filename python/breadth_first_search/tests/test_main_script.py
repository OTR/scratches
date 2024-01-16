"""
Test running `main.py` file as a script
"""
import unittest
from urllib.error import HTTPError, URLError

import config
import main
from .conftest import TestWebServer

BASE_DIR = config.BASE_DIR


BASE_DIR = config.BASE_DIR
TEST_HOST = config.TEST_WEB_SERVER_HOST
TEST_PORT = config.TEST_WEB_SERVER_PORT


class MainScriptTestCase(unittest.TestCase):
    """Test main.py file."""

    def test_get_host_from_url_positive(self):
        """
        Test that Crawler's staticmethod `get_host_from_url` trims down
        given url to second level domain.
        """
        url_with_query = "https://api.vk.com/getFriends?type=all"

        actual = main.Crawler.get_host_from_url(url_with_query)
        expected = "vk.com"

        self.assertEqual(actual, expected)

    def test_get_host_from_url_negative(self):
        """
        Test that Crawler's staticmethod `get_host_from_url` raises
        `UserWarning` when can't find netloc in given url
        """
        url_with_query = ""

        with self.assertRaises(UserWarning, ) as context_manager:
            actual = main.Crawler.get_host_from_url(url_with_query)

    def test_get_html_source_positive(self):
        """
        Test that Crawler instance's method `get_html_source` returns not
        empty and successfully decoded body.
        """
        url_to_get_body = "http://example.com"
        crawler = main.Crawler()

        actual = crawler.get_html_source(url_to_get_body)
        expected = str

        self.assertGreater(len(actual), 1000)  # body > 1000 bytes, not empty
        self.assertIsInstance(actual, expected)

    @unittest.skip("Not Implemented yet.")
    def test_find_all_hrefs(self):
        """"""
        source = ""
        path_to_test_source = BASE_DIR / "tests" / "test_data" / "requests.html"
        with open(path_to_test_source, "r") as cm:
            source = cm.read()
        crawler = main.Crawler()
        hrefs = crawler.find_all_hrefs(source)

        raise NotImplementedError


class WebServerRequiredTestCase(unittest.TestCase):
    """Suite of tests that require launching a test web server."""
    server: TestWebServer

    @classmethod
    def setUpClass(cls) -> None:
        """Launch a local web server with given response to serve."""
        cls.server = TestWebServer(host=TEST_HOST, port=TEST_PORT)
        cls.server_base_url = f"http://{TEST_HOST}:{TEST_PORT}"
        cls.server.register_view("/")
        cls.server.register_view("/cloudflare",
                                 with_status=403,
                                 with_headers=(("Server", "cloudflare"),)
                                 )

        cls.server.run_in_thread()
        cls.crawler = main.Crawler()

    def test_get_html_source_negative(self):
        """
        Test that Crawler instance's method `get_html_source` raises
        UserWarning if body of response is empty.
        """
        empty_body_url = self.server_base_url + "/"

        with self.assertRaises(UserWarning) as context_manager:
            self.crawler.get_html_source(empty_body_url)

        actual = context_manager.exception.args[0]
        expected = f"Response body for url `{empty_body_url}` is empty."
        self.assertEqual(actual, expected)

    def test_get_html_source_cloudflare(self):
        """Test catching CloudFlare protection. Negative test case."""
        fake_cloudflare_url = self.server_base_url + "/cloudflare"

        with self.assertRaises(HTTPError) as context_manager:
            self.crawler.get_html_source(fake_cloudflare_url)

        actual = context_manager.exception.headers["Server"]
        expected = "cloudflare"

        self.assertEqual(actual, expected)

    @classmethod
    def tearDownClass(cls) -> None:
        """Make sure child thread is terminated."""
        cls.server.stop_thread()
        assert cls.server.thread_is_alive() is None
