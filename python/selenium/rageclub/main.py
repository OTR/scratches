"""

"""

import configparser
from pathlib import Path

from selenium import webdriver

BASE_DIR = Path(__file__).resolve().parent


class GameBot(object):
    """"""
    def __init__(self, login, passwd):
        """"""
        self.browser = webdriver.Chrome()
        self.root_url = "https://rageclub.ru"
        self.pick_counter = 0
        self.login = login
        self.passwd = passwd
        self.global_cooldowns = {
            "pick_ore": 60,
        }


if __name__ == "__main__":
    pass
