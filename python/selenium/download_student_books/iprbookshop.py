"""
A script for downloading books from iprbookshop.ru, an e-books distribution website, using selenium and FireFox
"""

from time import sleep

from selenium import webdriver

from config import PATH_TO_DRIVER, IPRBOOK_LOGIN, IPRBOOK_PASSWORD, IPRBOOK_LINKS


class TorBrowser(object):
    """ A base class that prepares webdriver and holds methods for getting book's description and downloading it"""

    def __init__(self):
        """"""
        profile = webdriver.FirefoxProfile()
        profile.set_preference("network.proxy.type", 1)
        profile.set_preference("network.proxy.socks", '127.0.0.1')
        profile.set_preference("network.proxy.socks_port", 9150)
        profile.set_preference("network.proxy.socks_remote_dns", False)
        # Disable download dialogue
        profile.set_preference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream")
        profile.update_preferences()
        self.dr = webdriver.Firefox(firefox_profile=profile,
                                    executable_path=PATH_TO_DRIVER)
        self.links = []

    def login(self):
        """"""
        # Go to a root page
        self.dr.get("http://iprbookshop.ru")
        # Click on a dropdown menu
        element = self.dr.find_element_by_css_selector("a.dropdown-toggle")
        element.click()
        # Fill up a login field
        element = self.dr.find_element_by_css_selector("#login")
        element.send_keys(IPRBOOK_LOGIN)
        # Fill up a password field
        element = self.dr.find_element_by_css_selector("#password")
        element.send_keys(IPRBOOK_PASSWORD)
        # Sign in
        element = self.dr.find_element_by_css_selector("button.btn-success")
        element.click()
        sleep(5)  # FIXME: Replace with "wait_until" page is loaded

    def paging(self):
        """"""
        self.dr.find_element_by_css_selector("li.rb:nth-child(8)").click()
        sleep(5)  # FIXME:

        # While "next" link exists
        while len(self.dr.find_elements_by_css_selector("a.page-link.next")) > 0:
            last_window = self.dr.window_handles[-1]
            self.dr.switch_to.window(last_window)
            els = self.dr.find_elements_by_css_selector("a.pub-title")

            for el in els:
                last_window = self.dr.window_handles[-1]
                self.dr.switch_to.window(last_window)
                el.click()
                sleep(5)  # FIXME:
                # Go to the just opened window
                if len(self.dr.window_handles) > 1:
                    last_window = self.dr.window_handles[-1]
                    self.dr.switch_to.window(last_window)
                dl_btn = self.dr.find_elements_by_link_text("Скачать")
                if len(dl_btn) > 0:
                    dl_btn[0].click()
                    sleep(5)  # FIXME:
                self.dr.close()

            last_window = self.dr.window_handles[-1]
            self.dr.switch_to.window(last_window)
            self.dr.find_element_by_css_selector("a.page-link.next").click()
            sleep(5)  # FIXME:

        # last page
        # FIXME: refactor this, make this chunk of code reusable, DRY
        els = self.dr.find_elements_by_css_selector("a.pub-title")
        for el in els:
            # download
            el.click()
            sleep(5)  # FIXME:
            # Go to the just opened window
            if len(self.dr.window_handles) > 1:
                last_window = self.dr.window_handles[-1]
                self.dr.switch_to.window(last_window)
            dl_btn = self.dr.find_elements_by_link_text("Скачать")
            if len(dl_btn) > 0:
                dl_btn[0].click()
                sleep(5)  # FIXME:
            self.dr.close()

        print("Success")
        self.dr.close()

    def new_paging(self):
        """"""
        self.dr.find_element_by_css_selector("li.rb:nth-child(8)").click()
        sleep(5)  # FIXME:

        while len(self.dr.find_elements_by_css_selector("a.page-link.next")) > 0:
            els = self.dr.find_elements_by_css_selector("a.pub-title")
            for el in els:
                self.links.append(el.get_property("href"))
            self.dr.find_element_by_css_selector("a.page-link.next").click()
            sleep(5)  # FIXME:

        # last page
        els = self.dr.find_elements_by_css_selector("a.pub-title")

        for el in els:
            self.links.append(el.get_property("href"))

        print("Success")
        self.dr.close()

        with open(IPRBOOK_LINKS, "w") as f1:
            for link in self.links:
                f1.write(link+"\n")

    def load_from_file(self):
        """"""
        with open(IPRBOOK_LINKS, "r") as f1:
            lines = f1.read().split("\n")  # FIXME: readlines() method?

            for i in range(len(lines)):
                line = lines[i]
                self.dr.get(line)
                dl_btn = self.dr.find_elements_by_link_text("Скачать")
                if len(dl_btn) > 0:
                    dl_btn[0].click()
                    print("Downloaded {} from {}".format(i, len(lines)))
                    sleep(2)  # FIXME:

        self.dr.close()


if __name__ == "__main__":
    Bot = TorBrowser()
    Bot.login()
    Bot.load_from_file()
