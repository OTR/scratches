"""
Set up environment variables (such as login and password) before running tests.

$ SET GAMEBOT_TEST_LOGIN=alice@example.com  # FIXME: doesnt work
$ SET GAMEBOT_TEST_PASSWORD=My_v3ry_53cur3_p455w0rd
"""

import os
from pathlib import Path
import unittest

from selenium.webdriver.chrome.webdriver import WebDriver

from python.selenium.rageclub.main import GameBot

BASE_DIR = Path(__file__).resolve().parent


class TestDependencies(unittest.TestCase):
    """"""
    def test_chrome(self):
        """Test that Chrome is reachable."""
        pass

    def test_chromedriver(self):
        """Test that Chromedriver is reachable."""
        pass

    def test_selenium(self):
        """Test that proper version of selenium is installed."""
        pass


class TestRefGameBot(unittest.TestCase):
    """For refactoring purpose only."""
    def setUp(self) -> None:
        """Placeholder for GameBot instance."""
        self.Bot = None

    def test_init(self):
        """Test that browser window creates."""
        self.Bot = GameBot(login="Alice", passwd="Bob")
        self.assertIsInstance(self.Bot.browser, WebDriver)

    def test_login(self):
        """Test logging in on site."""
        login = os.environ.get("GAMEBOT_TEST_LOGIN")
        password = os.environ.get("GAMEBOT_TEST_PASSWORD")
        if not (login and password):
            self.fail("Set up environment variables. Read module docstring.")
        self.Bot = GameBot(login=login, passwd=password)

    def tearDown(self) -> None:
        """Don't forget to close browser after each test."""
        self.Bot.browser.close()


if __name__ == "__main__":
    unittest.main()
