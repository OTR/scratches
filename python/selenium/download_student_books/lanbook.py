"""
A script for downloading books.

from `lanbook.com` an e-books distribution website, using selenium and FireFox
"""

import os
from time import sleep

from selenium import webdriver

from config import PATH_TO_DRIVER, DOWNLOAD_DIR


class TorBrowser(object):
    """A base class that prepares webdriver and holds methods
    for getting book's description and downloading it"""

    def __init__(self, use_proxy=True):
        """If use_proxy parameter is given set up FireFox to use
        a SOCK5 proxy on ip 127.0.0.1 and port 9150
        A TOR's daemon should be running. It listens to port 9050 by default"""
        profile = webdriver.FirefoxProfile()

        if use_proxy:
            profile.set_preference("network.proxy.type", 1)
            profile.set_preference("network.proxy.socks", '127.0.0.1')
            profile.set_preference("network.proxy.socks_port", 9150)
            profile.set_preference("network.proxy.socks_remote_dns", False)

        # Disable download dialogue
        # This setting prevents a browser from
        # popping up a "Would you like to save this file" window
        profile.set_preference("browser.helperApps.neverAsk.saveToDisk",
                               "application/octet-stream")

        profile.update_preferences()
        self.dr = webdriver.Firefox(firefox_profile=profile,
                                    executable_path=PATH_TO_DRIVER)

        self.cwd = DOWNLOAD_DIR

        # Set up a delay for 10 seconds
        # https://selenium-python.readthedocs.io/waits.html#implicit-waits
        self.dr.implicitly_wait(10)

    def dump_book(self, book_id):
        """Get on book's page and go through each page
        which is represented as separated SVG file
        """
        result = self.get_book_info(book_id)
        pages = result["pages"]
        title = result["title"]

        self.get_or_create_dir(book_id, title)
        self.dr.get(f"https://e.lanbook.com/reader/book/{book_id}/#1")

        for i in range(1, pages + 1):
            print(f"Going on page number {i}")
            self.dr.get(
                f"https://fs1.e.lanbook.com/api/book/{book_id}/page/{i}/img"
            )
            data = self.dr.page_source
            with open(f"{i}.svg", "w") as f1:
                f1.write(data)
            sleep(0.1)

        print("Done")

    def get_book_info(self, book_id):
        """Go to a details page of a book to take a data
        such as an amount of pages and book's title"""
        cur_book_id = book_id
        self.dr.get(f"https://e.lanbook.com/book/{cur_book_id}")
        pages_tag = Bot.dr.find_element_by_xpath(
            "//dt[text()='Страниц']/following-sibling::dd[1]"
        )
        title_tag = Bot.dr.find_element_by_css_selector(
            "div.card-content.ng-star-inserted > h1"
        )
        title = title_tag.text
        # get rid of non alphabetic or numeric symbols
        # for using this title as correct filename
        title = "".join(x for x in title if x.isalnum() or ord(x) == 32)
        pages = int(pages_tag.text)

        return {"pages": pages, "title": title}

    def get_or_create_dir(self, book_id, title):
        """Try to make a directory and get into it"""
        os.chdir(self.cwd)
        dir_name = f"{book_id}_{title}"
        try:
            os.mkdir(dir_name)
        except OSError as err:
            print(err)
        os.chdir(os.path.join(os.getcwd(), dir_name))


if __name__ == "__main__":
    Bot = TorBrowser()
    # A list of book's ids we would like to download
    # FIXME: add argument parsing and take out these numbers into config file
    lst = ["92649",
           "74371",
           "3270",
           "52614",
           "2043",
           "121464"]
    try:
        for book_id in lst:
            # FIXME: add argument parsing for running in an interactive mode
            # book_id = input("Введите ID книги: ")
            Bot.dump_book(book_id)
    except KeyboardInterrupt:
        exit()
