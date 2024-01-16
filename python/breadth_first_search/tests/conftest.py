"""
Help functionality is placed here. Not actual tests.
"""
from http import HTTPStatus
from http.server import HTTPServer, SimpleHTTPRequestHandler
from threading import Thread
from typing import Callable, Union

import config


class TestRouter(object):
    """"""
    default_headers = [
        ("Content-type", "text/html"),
    ]

    def __init__(self):
        """"""
        # Default `view` that is returned when accessing not registered url.
        self.page_not_found_view = self._create_view(
            with_status=HTTPStatus.NOT_FOUND
        )
        self.__urlpatterns = {}

    def set_view(self, at_url: str, view: Callable) -> None:
        """
        A setter method for `self.urlpatterns`. Beware: overrides previous
        value of key if called twice.

        :param at_url:
        :param view:
        """
        self.__urlpatterns[at_url] = view

    def get_view(self, at_url: str) -> Callable:
        """
        Get a view mapped at a URL in object's dictionary,
        if KeyError return default response with 404 code

        :param at_url: a URL passed on from HTTP Request
        :return: a view function
        """
        return self.__urlpatterns.get(at_url, self.page_not_found_view)

    def _create_view(self, **kwargs) -> Callable:
        """"""
        with_body = kwargs.get("with_body", "").encode("UTF-8")
        with_status = kwargs.get("with_status", HTTPStatus.OK)
        with_headers = kwargs.get("with_headers", self.default_headers)

        def view(this, body=with_body, status=with_status,
                 headers=with_headers):
            """FIXME: combine common send method for send_response and
            send_code"""
            this.send_response(status)
            for keyword, value in headers:
                this.send_header(keyword, value)
            this.end_headers()
            this.wfile.write(body)

        return view


class GetRequestHandler(SimpleHTTPRequestHandler):
    """A class to imitate HTTP response from a web server. Handles only GET
    requests."""

    def __init__(self, *args, **kwargs):
        """"""
        # Everything you put after calling `super` wouldn't be executed
        # Because in `socketserver.BaseRequestHandler.__init__` called
        # `self.handle`, which is overridden in
        # `http.server.BaseHTTPRequestHandler` to run within infinite loop
        super(GetRequestHandler, self).__init__(*args, **kwargs)

    def do_GET(self) -> None:
        """Get a view associated with a URL from request and execute it."""
        view = self.server.router.get_view(self.path)
        view(self)

    def send_response(self, code: int,
                      message: Union[str, None] = None) -> None:
        """
        Override method of parent class to not send `Server` and `Date`
        headers.

        :param code: HTTP status code to send
        :param message: verbose description about status to display in body
        """
        self.log_request(code)
        self.send_response_only(code, message)



class TestWebServer(object):
    """TODO: write me"""

    def __init__(self, host: str, port: int):
        """
        Create an instance of builtin web server.
        TODO: refactor __init__ add `register_url` method, make it possible
         to run in silent mode
         i.e. get rid of [09/Dec/2021 23:23:11] "GET / HTTP/1.1" 200 in stdout
        :param host:
        :param port:
        """
        self.__server = HTTPServer((host, port), GetRequestHandler)
        self.__server.router = TestRouter()
        self.__thread = None

    def register_view(
            self,
            at_url: str,
            with_body: str = "",
            with_status: int = HTTPStatus.OK,
            with_headers: tuple[tuple] = (("Content-type", "html/txt"),)
    ) -> None:
        """
        Set up the server to serve given response at given url.
        Should be called in __init__ before `serve forever`

        :param at_url:
        :param with_body:
        :param with_status:
        :param with_headers:
        :return:
        """
        view = self.__server.router._create_view(with_body=with_body,
                                                 with_status=with_status,
                                                 with_headers=with_headers)
        self.__server.router.set_view(at_url=at_url, view=view)

    def __start(self) -> None:
        """Start a web server serving forever."""
        with self.__server:
            self.__server.serve_forever()

    def __stop(self) -> None:
        """Stop a web server."""
        self.__server.shutdown()

    def run_in_thread(self) -> None:
        """Run a web server in separate thread. Keep a reference to it in
        `self.thread`"""
        if self.__thread is None:
            self.__thread = Thread(target=self.__start, name="local_web_server")
            self.__thread.setDaemon(True)
            self.__thread.start()

    def stop_thread(self) -> None:
        """Stop a web server, then wait until thread terminates."""
        if self.__thread is not None:
            self.__stop()
            self.__thread.join()
            self.__thread = None

    def thread_is_alive(self) -> Union[bool, None]:
        """Getter to thread's method `is_alive`"""
        return self.__thread.is_alive() if self.__thread is not None else None
