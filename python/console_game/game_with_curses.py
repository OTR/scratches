"""
This is some of my very first python programs
"""
import curses
import sys
from collections import namedtuple


GAME_OBJECTS = []
BORDER = '''##################
#                #
#                #
#                #
#                #
#                #
#                #
#                #
#                #
#                #
#                #
#                #
#                #
#                #
#                #
#                #
#                #
##################'''

GAME_COLOR = namedtuple("CursesColor", "num_pair fg_color bg_color")


class GameBoard(object):
    """"""
    BLACK_COLOR = GAME_COLOR(1, curses.COLOR_BLACK, curses.COLOR_WHITE)
    MAGENTA_COLOR = GAME_COLOR(2, curses.COLOR_MAGENTA, curses.COLOR_BLACK)
    RED_COLOR = GAME_COLOR(3, curses.COLOR_RED, curses.COLOR_RED)

    def __init__(self, start_pos, avatar):
        """"""
        self.screen = curses.initscr()
        self.initiate_curses_colors()
        self.start_pos = start_pos
        self.pos = start_pos
        self.avatar = avatar
        self.action = []

    @staticmethod
    def initiate_curses_colors():
        """"""
        curses.start_color()
        curses.init_pair(1, curses.COLOR_BLACK, curses.COLOR_WHITE)
        curses.init_pair(2, curses.COLOR_MAGENTA, curses.COLOR_BLACK)
        curses.init_pair(3, curses.COLOR_RED, curses.COLOR_BLACK)

    def render(self, pos=None):
        """"""
        if pos is None:
            pos = [1, 1]

        if pos == [1, 1]:
            self.screen.addstr(
                self.start_pos[0], self.start_pos[1], self.avatar,
                curses.color_pair(2)
            )
        else:
            self.screen.addstr(
                pos[0], pos[1], self.avatar, curses.color_pair(2)
            )
            self.pos = pos

    def down(self):
        """"""
        if self.pos[0] < 16:
            self.pos = [self.pos[0] + 1, self.pos[1]]

        self.render(self.pos)
        self.action = [self.pos[0] + 1, self.pos[1]]
        self.screen.chgat(
            self.action[0], self.action[1], 1,curses.color_pair(1)
        )

    def up(self):
        """"""
        if self.pos[0] > 1:
            self.pos = [self.pos[0] - 1, self.pos[1]]

        self.render(self.pos)
        self.action = [self.pos[0] - 1, self.pos[1]]
        self.screen.chgat(
            self.action[0], self.action[1], 1, curses.color_pair(1)
        )

    def left(self):
        """"""
        if self.pos[1] > 1:
            self.pos = [self.pos[0], self.pos[1] - 1]

        self.render(self.pos)
        self.action = [self.pos[0], self.pos[1] - 1]
        self.screen.chgat(
            self.action[0], self.action[1], 1, curses.color_pair(1)
        )

    def right(self):
        """"""
        if self.pos[1] < 16:
            self.pos = [self.pos[0], self.pos[1] + 1]

        self.render(self.pos)
        self.action = [self.pos[0], self.pos[1] + 1]
        self.screen.chgat(
            self.action[0], self.action[1], 1, curses.color_pair(1)
        )

    def dig(self):
        """"""
        self.screen.addstr(
            self.action[0], self.action[1], "T", curses.color_pair(3)
        )
        GAME_OBJECTS.append((self.action[0], self.action[1], "T"))

    def draw_border(self):
        """"""
        self.screen.addstr(0, 0, BORDER)
        self.screen.addstr(
            21, 2, "Lv:%s | Eq:%s | Hit:%s" % ("1", "S.Axe", "50/50")
        )

    def move_character(self, key):
        """"""
        self.screen.clear()
        self.draw_border()
        if key == "down":
            self.down()
        elif key == "up":
            self.up()
        elif key == "left":
            self.left()
        elif key == "right":
            self.right()
        else:
            print("Error in Move()")
        self.screen.refresh()


def main() -> None:
    """"""
    try:
        player_start_pos = (1, 1)
        player_character = "@"
        game = GameBoard(player_start_pos, player_character)
        game.draw_border()
        game.render()
        game.screen.refresh()
        while True:
            key = game.screen.getch(21, 0)
            if key == 115:
                game.move_character("down")
            elif key == 119:
                game.move_character("up")
            elif key == 97:
                game.move_character("left")
            elif key == 100:
                game.move_character("right")
            elif key == 103:
                game.dig()
            elif key == 121:
                game.screen.clear()
                print(GAME_OBJECTS)
            else:
                pass
            if GAME_OBJECTS:
                for obj in GAME_OBJECTS:
                    game.screen.addstr(obj[0], obj[1], obj[2])
    except KeyboardInterrupt as err:
        sys.exit()
    finally:
        curses.endwin()


if __name__ == '__main__':
    main()
