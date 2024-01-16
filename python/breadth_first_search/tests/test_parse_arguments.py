"""
Test `parse_arguments` function from main.py for named arguments.

Arguments are:
    --host to start crawling from given host,
    e.g., python main.py --host yandex.ru
    --seed to start crawling from provided hosts placed in a plain text file,
    one per line (LF terminated)
    e.g., python main.py --seed ./app_data/seed.txt
    --pickle to start from saved working condition, object variables `to_see`
    and `seen` serialized using pickle module,
    e.g., python main.py --pickle ./app_data/working_state.pickle
    --database use SQLite3 database for collecting visited hosts and hosts to
    visit,
    e.g., python main.py --database ./app_data/working_state.sqlite3
"""
import subprocess
import unittest

import config

START_HOST = config.START_HOST


class ParseArgumentsTestCase(unittest.TestCase):
    """
    Test correct working of `parse_arguments` function with different
    arguments.

    CODE_PATTERN - setup code before printing out parsed arguments
    """
    CODE_PATTERN = "from main import parse_arguments; " \
                   "args = parse_arguments(); " \
                   "print({args_to_parse_as_str})"

    @staticmethod
    def run_and_read_from_popen(cmd: str) -> tuple[str, str, str]:
        """
        Provide given command as an argument to `subprocess.run` function,
        read and return the result.

        :param cmd: a command to run
        :return: output of the command
        """
        # TODO: use `sys.executable` instead of running python from console
        # https://www.digitalocean.com/community/tutorials
        # /how-to-use-subprocess-to-run-external-programs-in-python-3-ru
        complete_process = subprocess.run(cmd,
                                          capture_output=True,
                                          shell=True,
                                          text=True)
        stdin = getattr(complete_process, "stdin", "")
        stdout = getattr(complete_process, "stdout", "")
        stderr = getattr(complete_process, "stderr", "")

        return stdin, stdout, stderr

    @classmethod
    def complete_cmd_pattern(cls,
                             args_to_parse: tuple[str],
                             cli_args: tuple[str]) -> str:
        """
        TODO: write me

        code_to_execute - a python code that is provided to python interpreter
        as a value of argument `-c`

        :param args_to_parse:
        :param cli_args: arguments the would be provided to python interpreter
        :return: completed string to provide as first argument to
            `subprocess.run`
        """
        args_to_parse_as_str = ", ".join(
            ["args.{arg}".format(arg=arg) for arg in args_to_parse]
        )
        code_to_execute = cls.CODE_PATTERN.format(
            args_to_parse_as_str=args_to_parse_as_str
        )
        cli_args_as_str = " ".join(cli_args)
        cmd = 'python -c "{code_to_execute}" {cli_args_as_str}'.format(
            code_to_execute=code_to_execute,
            cli_args_as_str=cli_args_as_str
        )

        return cmd

    def test_host_argument(self):
        """
        Test parsing of `--host` argument,
        e.g., `$ python main.py --host example.com`
        """
        args_to_parse = ("host",)
        host_to_parse = "example.com"
        cli_args = (
            "--host {host}".format(host=host_to_parse),
        )
        cmd = self.complete_cmd_pattern(args_to_parse, cli_args)
        stdin, stdout, stderr = self.run_and_read_from_popen(cmd)

        actual = stdout.rstrip("\n")
        expected = host_to_parse

        self.assertEqual(actual, expected)

    def test_missed_host_argument(self):
        """
        Negative test case, test returning error message when `--host` argument
        was mentioned, but value wasn't provided.
        e.g., `$ python main.py --host`
        """
        # TODO: catch `Process finished with exit code 2`
        args_to_parse = ("host",)
        cli_args = (
            "--host",
        )
        cmd = self.complete_cmd_pattern(args_to_parse, cli_args)
        stdin, stdout, stderr = self.run_and_read_from_popen(cmd)

        actual = stderr.rstrip("\n")
        expected = "error: argument --host: expected one argument"

        # Reminder: member, container
        self.assertIn(expected, actual)
