### How to install

#### For Windows with MinGW64
    $ pip install pipenv
    $ git clone https://github.com/OTR/scratches.git
    $ cd scratches/python/selenium/rageclub_game_bot
    $ pipenv install
    $ pipenv shell

Install proper version of Chrome web browser.


Download proper version of webdriver in working directory:

    $ curl -o chromedriver.zip https://chromedriver.storage.googleapis.com/96.0.4664.45/chromedriver_win32.zip
    $ unzip chromedriver.zip
    $ rm chromedriver.zip

Run main.py

    $ python main.py

#### Dependencies

Tested with following Chrome version:

* [Chrome](https://www.google.com/intl/en_us/chrome/) 96.0.4664.45 (64 bit) 
  Windows
  
* Chrome [Webdriver](https://chromedriver.storage.googleapis.com/index.html?path=96.0.4664.45/) 96.0.4664.45

### TODO List

* When first run check for Chrome, Chromedriver, selenium

* Check config.ini . If it's blank ask for credentials