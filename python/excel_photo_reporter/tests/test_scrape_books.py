"""

"""

from io import IOBase, BytesIO, FileIO
import pathlib
import unittest
from scripts.scrape_books_from_amazon import AmazonParser


CWD = pathlib.Path(__file__).parent.parent


class TestImagesFromFirstPage(unittest.TestCase):
    """Get book's image src and alt name for books from first page."""
    @classmethod
    def setUpClass(cls) -> None:
        """"""
        cls.parser = AmazonParser(log_level="debug")
        cls.keyword = "python"
        cls.page = 1
        cls.this_test_output_dir = CWD / "tests" / "output" / "scrape_books"

    # TODO: refactor. expected result is not consistent
    @unittest.skip("Expected result is outdated. Search page has been changing")
    def test_scrape_books_image_name(self):
        """Compare with hard-coded result to ensure refactoring."""
        expected = [{'image_src': 'https://m.media-amazon.com/images/I/71sL0Qpd+YL._AC_UY218_.jpg',
                     'image_name': 'Python Crash Course, 2nd Edition: A Hands-On, Project-Based Introduction to Programming'},
                    {'image_src': 'https://m.media-amazon.com/images/I/71Ekeps7VeS._AC_UY218_.jpg',
                     'image_name': 'Python Programming for Beginners: The Ultimate Guide for Beginners to Learn Python Programming: Crash Course on Python Pro...'},
                    {'image_src': 'https://m.media-amazon.com/images/I/715PNi5Wa2L._AC_UY218_.jpg',
                     'image_name': 'Automate the Boring Stuff with Python, 2nd Edition: Practical Programming for Total Beginners'},
                    {'image_src': 'https://m.media-amazon.com/images/I/614DWyHLwWS._AC_UY218_.jpg',
                     'image_name': 'Learning Python, 5th Edition'},
                    {'image_src': 'https://m.media-amazon.com/images/I/81okqJBqNFL._AC_UY218_.jpg',
                     'image_name': 'The Big Book of Small Python Projects: 81 Easy Practice Programs'},
                    {'image_src': 'https://m.media-amazon.com/images/I/91lrhlw-XcL._AC_UY218_.jpg',
                     'image_name': 'Python for Data Analysis: Data Wrangling with Pandas, NumPy, and IPython'},
                    {'image_src': 'https://m.media-amazon.com/images/I/81Ce8tGNYzL._AC_UY218_.jpg',
                     'image_name': 'Python for Excel: A Modern Environment for Automation and Data Analysis'},
                    {'image_src': 'https://m.media-amazon.com/images/I/71zu+VwkThL._AC_UY218_.jpg',
                     'image_name': 'Python for Beginners: Learn Python Programming With No Coding Experience in 7 Days: The Easiest & Quickest Way to Learn Py...'},
                    {'image_src': 'https://m.media-amazon.com/images/I/91yDOwYAnWS._AC_UY218_.jpg',
                     'image_name': 'Black Hat Python, 2nd Edition: Python Programming for Hackers and Pentesters'},
                    {'image_src': 'https://m.media-amazon.com/images/I/71zoiqPRd6S._AC_UY218_.jpg',
                     'image_name': "Murach's Python Programming (2nd Edition)"},
                    {'image_src': 'https://m.media-amazon.com/images/I/81faPYg2QCS._AC_UY218_.jpg',
                     'image_name': 'Robust Python: Write Clean and Maintainable Code'},
                    {'image_src': 'https://m.media-amazon.com/images/I/71nSzDs+mmS._AC_UY218_.jpg',
                     'image_name': 'Python Object-Oriented Programming: Build robust and maintainable object-oriented Python applications and libraries, 4th E...'},
                    {'image_src': 'https://m.media-amazon.com/images/I/91Hg46z6wcL._AC_UY218_.jpg',
                     'image_name': 'COMPUTER PROGRAMMING AND CYBER SECURITY FOR BEGINNERS: PYTHON: Give your professional possibilities a boost with the Most ...'},
                    {'image_src': 'https://m.media-amazon.com/images/I/71RiBEY6mWL._AC_UY218_.jpg',
                     'image_name': 'Fluent Python: Clear, Concise, and Effective Programming'},
                    {'image_src': 'https://m.media-amazon.com/images/I/91NS-QR0PGL._AC_UY218_.jpg',
                     'image_name': 'Python All-in-One For Dummies (For Dummies (Computer/Tech))'},
                    {'image_src': 'https://m.media-amazon.com/images/I/61E3OdHLsVL._AC_UY218_.jpg',
                     'image_name': 'Deep Learning with Python, Second Edition'}]

        self.books_on_first_page = self.parser.main(
            keyword=self.keyword, page=self.page)
        keyfunc = lambda dct: dct["image_name"]
        self.assertEqual(sorted(self.books_on_first_page, key=keyfunc),
                         sorted(expected, key=keyfunc))

    def test_download_images(self):
        """"""
        books_on_first_page = self.parser.main(
            keyword=self.keyword, page=self.page)
        for book in books_on_first_page:
            src = book["image_src"]
            response = self.parser.opener.open(src)
            filename = src.split("/")[-1]  #FIXME: use urllib.parse.urlparse
            path_to_save_img = self.this_test_output_dir / filename
            with open(path_to_save_img, "wb") as fd:
                fd.write(response.read())


class TestAmazonParser(unittest.TestCase):
    """Test that AmazonParser class works the same after refactoring."""
    @classmethod
    def setUpClass(cls) -> None:
        """"""
        cls.parser = AmazonParser(log_level="debug")
        cls.keyword = "python"
        cls.page = 1
        cls.this_test_output_dir = CWD / "tests" / "output" / "scrape_books"

    def setUp(self) -> None:
        """TODO: use fixtures per certain tests not for all"""
        self.in_memory = BytesIO()


    def test_create_url(self):
        """Test AmazonPaser's constructor combined URL correctly."""
        pattern = "https://www.amazon.com/s?k={keyword}&i=stripbooks-intl-ship&page={page}"
        expected = pattern.format(keyword=self.keyword, page=self.page)
        actual = self.parser.create_url(keyword=self.keyword,
                                        page=self.page)

        self.assertEqual(expected, actual)

    def __get_search_page(self, save_as: str = "file"):
        """Download and save a page on a disk. Used once for `caching` web page.
        :param save_as:
            "file" to save on a disk to hardcoded path,
            "BytesIO" to keep in memory"""
        response = self.parser.get_search_page(keyword=self.keyword,
                                               page=self.page)  # Bytes
        if response is None:
            raise Exception("No response")

        if save_as == "file":
            path_to_save_webpage = self.this_test_output_dir / \
                                   "search_books_on_python.html"
            with open(path_to_save_webpage, "wb") as fd:
                fd.write(response.read())
        elif save_as == "BytesIO":
            self.in_memory.write(response.read())
        else:
            raise ValueError("Unexpected value of argument `save_as`")

    @unittest.skipIf(True is False, "For refactoring use only")
    def test_keeping_in_memory(self):
        """"""
        self.__get_search_page(save_as="BytesIO")

        member = "<!doctype html>"
        self.in_memory.seek(0)
        # TODO: possible refactoring. Why even keep io.BytesIO object instead
        #  of bytes itself
        container = self.in_memory.read().decode("UTF-8")
        greatest = len(container)
        lowest = 100 * 1024  # 100 KB

        self.assertIn(member, container, msg="Has HTML magic tag")
        self.assertGreater(greatest, lowest, msg="Has some content")

    def test_parse_books_on_page(self):
        """"""
        self.__get_search_page(save_as="BytesIO")
        self.in_memory.seek(0)
        books = self.parser.parse_books_on_page(self.in_memory)

        self.assertIs(type(books), list, msg="Ensure getting list not None")
        self.assertEqual(len(books), 16, msg="16 results per page")

    def test_not_parse_books_with_empty_resp(self):
        """Assume there is an empty byte string in_memory."""
        response = BytesIO()
        no_books = self.parser.parse_books_on_page(response)
        self.assertIsNone(no_books, msg="Ensure NOT parsing HTML tree from "
                                        "empty string")

    def test_not_parse_books_with_dummy_resp(self):
        """Assume there is a valid HTML but with no sense in_memory."""
        response = BytesIO()
        response.write(b"<html></html>")
        response.seek(0)
        no_books = self.parser.parse_books_on_page(response)
        self.assertIsNone(no_books, msg="Ensure NOT parsing HTML tree from "
                                        "dummy HTML")


if __name__ == "__main__":
    unittest.main()
