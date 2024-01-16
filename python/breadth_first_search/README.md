### Requirements:

Python 3.5 or greater

### How to install:

#### via Pip:

```bash
$ python -m venv search_venv
$ cd search_venv
(Linux) $ source bin/activate
(Windows) $ Scripts\activate.bat
$ git clone https://github.com/OTR/breadth_first_search.git search
$ cd search
$ python main.py
```

### How to run tests:

```commandline
$ python -m unittest
```

To run test coverage:

`$ coverage run - unuttest`

To see results run:

`$ coverage html`

Then run a command for your preferred web browser:

`$ firefox ./htmlcov/index.html`


### Usage:

#### Running without arguments (smoke test mode)
If you run the script without any arguments from command line, i.e.,

```commandline
$ python main.py
```

by default, it will start from hardcoded in `config.py` host `yandex.ru`

#### Display available options

To display help message, run:

```commandline
$ python main.py --help
```

#### Using `--host` argument

To make the script walking through URL's from exact host run the following:

```commandline
$ python main.py --host example.com
```

Where `example.com` is the host to start from

### TODO List:
~~* Make `logger` object visible everywhere or refactor the code with OOP style~~

~~* Make log messages more informative~~

* Add to crawler states:
```python
last_priority = 0
list_priority = {}
```

* Add urls like `https://google.com` to internal urls if origin host
  (first argument) is `google.com` too, but not `google.ru`

* Add urls with third level domain name to internal urls if origin host is
  the same but only second level domain name

* Try to request a page by HTTPS first, then by HTTP


* Use `URLOpener` or `FancyURLOpener` object instead of `OpenerDirector`

* Add redirect handling into `get_html_source` function, or at least
  explicitly say in log file that we were redirected while trying to request
  given URL
  

* add handling of `mailto:you@example.com` and `tel:8-800-555-5555` href's

* fix Issue #4

* make logger object be a class attribute

* catch the following exception at line 75
```
urllib.error.URLError: <urlopen error [WinError 10060] Попытка установить
соединение была безуспешной, т.к. от другого компьютера за требуемое время
не получен нужный отклик, или было разорвано уже установленное соединение
из-за неверного отклика уже подключенного компьютера>
```

* Set `default encoding` of python interpreter explicitly to UTF-8, it tries to 
  write log massages in cp1251 catching WinError

* Use separate log file for tests

* Split `Crawler.get_html_source` method into small pieces

* Replace IO calls in tests with unittest.mock.patch

### Found BUGS:

**Placed in [Open Issues](https://github.com/OTR/breadth_first_search/issues)**

* Need to add handling of double slashes:

```
Found 4 internal URL's:
    //yandex.ru/all
    //mobile.yandex.ru/?from=desktop_morda_more
    //yandex.ru/video
    //yandex.ru/images/
```

* Twitter redirects to different pages depend on `User-Agent` header

* The script parses `auto.ru` too long, find out the reason