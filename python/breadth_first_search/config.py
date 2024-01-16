"""
Default configuration file.
"""
import logging
import re
from pathlib import Path
from re import compile

# Path to root dir of the project
BASE_DIR = Path(__file__).resolve().parent
# ______________________________________________________________________________
#                         LOGGING SECTION
# ______________________________________________________________________________
# Plain text file to keep logs from the script
LOG_FILE = BASE_DIR / "log.txt"

# Default logging level, messages at that level will be displayed in log file
LOG_LEVEL = logging.DEBUG

# Log message format
# See: https://docs.python.org/3/library/logging.html#logging.Formatter
LOG_MESSAGE_FORMAT = "%(asctime)-8s %(levelname)-8s %(message)s"

# Date format used for logging
# See: https://docs.python.org/3/library/time.html#time.strftime
LOG_DATE_FORMAT = "%d.%m.%Y %H:%M:%S"
# ______________________________________________________________________________
#                         WEB CRAWLER SECTION
# ______________________________________________________________________________
# User Agent Header for HTTP Requests
USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:94.0) " \
             "Gecko/20100101 Firefox/94.0"

# Regular expression to find all anchor HTML tags
# and put `href` attribute in a group
HREF_REGEXP = compile(r'<a.+?href="(?P<href>.+?)".*?>.*?</a>', flags=re.S)
# ______________________________________________________________________________
#                         WORKING STATE SECTION
# ______________________________________________________________________________
# A host to start crawling from
START_HOST = "yandex.ru"

# to start crawling from provided hosts placed in a plain text file,
# one per line (LF terminated)
SEED_FILE = BASE_DIR / "app_data" / "seed.txt"

# Use SQLite3 database to save working state between runs (sessions)
# Path to database as file to save/load working state
DATABASE_FILE = BASE_DIR / "app_data" / "working_state.sqlite3"

# Path to pickle file to save/load working state
PICKLE_FILE = BASE_DIR / "app_data" / "working_state.pickle"
# ______________________________________________________________________________
#                         HOST FILTERING SECTION
# ______________________________________________________________________________
# Hosts that would be ignored (not visited) while crawling
BLACK_LISTED_HOSTS = (
    compile(r'(^|\.)yandex\.ru'),
    compile(r'(^|\.)google\.[a-z]+?'),
    compile(r'(^|\.)ya\.ru'),
    compile(r'(^|\.)apple\.com'),
    compile(r'(^|\.)vk\.com'),
    compile(r'(^|\.)twitter\.com'),
    compile(r'(^|\.)youtube\.com'),
    compile(r'(^|\.)vkontakte\.ru'),
    compile(r'(^|\.)qip\.ru'),
    compile(r'(^|\.)mail\.ru'),
    compile(r'(^|\.)adobe\.com'),
    compile(r'(^|\.)facebook\.com'),
    compile(r'(^|\.)wikipedia\.org'),
)

# Register filters (by adding new item in tuple) that would be applied on every
# host before adding it into `visiting queue`, sequence of hosts to visit
# blacklist - to filter out blacklisted hosts, i.e., they would not be
#             followed if met on a web page
# has_in_url - to add into `visiting queue` only web sites that respond with OK
#              on requested url, e.g., has_in_url("/rss") makes the crawler
#              follow `example.com` if that respond with HTTP 20x codes on
#              request `http://example.com/rss`. TODO: use regular expressions
FILTERS_TO_APPLY = (
    # blacklist,
    # has_in_url
)
# ______________________________________________________________________________
#                         TESTING SECTION
# ______________________________________________________________________________
# Default host for running test web server
TEST_WEB_SERVER_HOST = "127.0.0.1"

# Default port for running test web server
TEST_WEB_SERVER_PORT = 8888
# ______________________________________________________________________________
