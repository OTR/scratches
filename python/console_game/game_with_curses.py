"""
acaicabb
This is some of my very first python programs
"""
import curses


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


def draw_border():
    """"""
    myscreen.addstr(0, 0, BORDER)
    myscreen.addstr(21, 2, "Lv:%s | Eq:%s | Hit:%s" % ("1", "S.Axe", "50/50"))


def move_character(key):
    """"""
    myscreen.clear()
    draw_border()
    if key == "down":
        hero.down()
    elif key == "up":
        hero.up()
    elif key == "left":
        hero.left()
    elif key == "right":
        hero.right()
    else:
        print("Error in Move()")
    myscreen.refresh()


class Character(object):
    """"""

    def __init__(self, screen, start_pos, avatar):
        """"""
        self.screen = screen
        self.start_pos = start_pos
        self.pos = start_pos
        self.avatar = avatar
        self.action = []

    def render(self, pos=None):
        """"""
        if pos is None:
            pos = [1, 1]
        if pos != [1, 1]:
            self.screen.addstr(pos[0], pos[1], self.avatar,
                               curses.color_pair(2)
                               )
            self.pos = pos
        else:
            self.screen.addstr(self.start_pos[0], self.start_pos[1],
                               self.avatar)

    def down(self):
        """"""
        if self.pos[0] < 16:
            self.pos = [self.pos[0] + 1, self.pos[1]]
        else:
            pass
        self.render(self.pos)
        self.action = [self.pos[0] + 1, self.pos[1]]
        self.screen.chgat(self.action[0], self.action[1], 1,
                          curses.color_pair(1))

    def up(self):
        """"""
        if self.pos[0] > 1:
            self.pos = [self.pos[0] - 1, self.pos[1]]
        else:
            pass
        self.render(self.pos)
        self.action = [self.pos[0] - 1, self.pos[1]]
        self.screen.chgat(self.action[0], self.action[1], 1,
                          curses.color_pair(1))

    def left(self):
        """"""
        if self.pos[1] > 1:
            self.pos = [self.pos[0], self.pos[1] - 1]
        else:
            pass
        self.render(self.pos)
        self.action = [self.pos[0], self.pos[1] - 1]
        self.screen.chgat(self.action[0], self.action[1], 1,
                          curses.color_pair(1))

    def right(self):
        """"""
        if self.pos[1] < 16:
            self.pos = [self.pos[0], self.pos[1] + 1]
        else:
            pass
        self.render(self.pos)
        self.action = [self.pos[0], self.pos[1] + 1]
        self.screen.chgat(self.action[0], self.action[1], 1,
                          curses.color_pair(1))

    def dig(self):
        """"""
        self.screen.addstr(self.action[0], self.action[1], "T",
                           curses.color_pair(3))
        GAME_OBJECTS.append((self.action[0], self.action[1], "T"))


if __name__ == '__main__':
    try:
        myscreen = curses.initscr()
        curses.start_color()
        curses.init_pair(1, curses.COLOR_BLACK, curses.COLOR_WHITE)
        curses.init_pair(2, curses.COLOR_MAGENTA, curses.COLOR_BLACK)
        curses.init_pair(3, curses.COLOR_RED, curses.COLOR_BLACK)
        draw_border()
        hero = Character(myscreen, [1, 1], "@")
        hero.render()
        myscreen.refresh()
        while True:
            key = myscreen.getch(21, 0)
            if key == 115:
                move_character("down")
            elif key == 119:
                move_character("up")
            elif key == 97:
                move_character("left")
            elif key == 100:
                move_character("right")
            elif key == 103:
                hero.dig()
            elif key == 121:
                myscreen.clear()
                print(GAME_OBJECTS)
            else:
                pass
            if GAME_OBJECTS:
                for obj in GAME_OBJECTS:
                    myscreen.addstr(obj[0], obj[1], obj[2])
    finally:
        curses.endwin()
