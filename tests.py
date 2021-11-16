"""

"""

from pathlib import Path
import unittest

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


if __name__ == "__main__":
    unittest.main()
