"""
Fill up your credentials and path to geckodriver.exe here.
"""
from pathlib import Path


BASE_DIR = Path(__file__).parent

# A path to executable webdriver for FireFox
# FIXME: Do not use absolute and OS dependent paths
PATH_TO_DRIVER = r"C:\Users\User\projects\python\selenium\geckodriver.exe"

# A directory for saving downloaded books in
DOWNLOAD_DIR = r"D:\lanbook"

# Credentials for signing in IPRBOOKSHOP.ru site.
# Bad design, don't keep them like this
IPRBOOK_LOGIN = "jhon.smith@gmail.com"
IPRBOOK_PASSWORD = "SuP3R_S3cR3T"  # FIXME: don't keep passwords in a raw text

# A path to a prepared list of links on certain books
IPRBOOK_LINKS = "iprbookshop_links.txt"  # FIXME: make absolute path finding
