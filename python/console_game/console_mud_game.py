"""
ccaccabc
This is some of my very first python programs
"""
import shelve
import sys
from random import randint, choice


__version__ = '0.0.3'
commands = ["n",  # go to north
            "s",  # go to south
            "w",  # go to west
            "e",  # go to east
            "p",  # pick up resources
            "exit",  # exit from game
            "randturn"]  # rand location

location_first_name = ['Бурая', 'Смертельная', 'Счастливая', 'Дикая',
                       'Кровавая', 'Зеленая', 'Верхняя', 'Заколдованная',
                       'Древняя']

location_second_name = ['Роща', 'Поляна', 'Побережье', 'Овраг', 'Лес',
                        'Пустыня', 'Горы', 'Озеро', 'Болото', 'Ущелье', 'Оазис']


class BasicLocation(object):
    """"""
    def __init__(self, resource=None, name=None):
        """"""
        self.resource = resource
        if name:
            self.name = name
        else:
            self.name = 'Тестовая локация #%d' % randint(0, 255)

    def sub_resource(self):
        """"""
        if self.resource:
            self.resource -= 1
            return 1
        else:
            return 0

    def add_resource(self):
        """Generate new resource."""
        pass

    def link(self):
        """Link a location to another, make portal."""
        pass


class ShelveLocation(BasicLocation):
    """"""

    def __init__(self, link_db, loc, resource=None, name=None):
        """"""
        BasicLocation.__init__(self, resource, name)
        self.link_db = link_db
        self.loc = loc

    def sub_resource(self):
        """"""
        if self.resource:
            self.resource -= 1
            self.transfer()
            return 1
        else:
            return 0

    def transfer(self):
        """"""
        tmp = self.link_db[self.loc]
        tmp[1] = self.resource
        self.link_db[self.loc] = tmp


class ShelveDB(object):
    """"""
    def __init__(self, radius, base=None):
        """"""
        if base:
            self.db = shelve.open(base)
        else:
            self.db = shelve.open('./db.dat')
        self.radius = radius
        self.prefix = len(str(radius * 2))
        self.sl = self.make_start_location()
        self.db.sync()

    def make_dungeon(self):
        """Make a dungeon."""
        pass

    def make_start_location(self, sl=None):
        """"""
        if sl is None:
            _null = '0' * self.prefix
            sl = '%s:%s' % (_null, _null)
        else:
            pass  # Not standard start location
        name = "Спуск в подземелье"
        resource = randint(0, 255)
        self.db[sl] = [name, resource]  # lw=None,ln=None,ls=None,le=None]
        # lw - link to west location etc
        return sl


def load_rand_locate():
    """Для режима без карты вообще."""
    global location_first_name, location_second_name
    name = "%s %s" % (choice(location_first_name), choice(location_second_name))
    location = BasicLocation(resource=randint(0, 5), name=name)
    return location


def generate_world(radius):
    """проверка на существование базы"""
    try:
        pass
    except:
        pass
    world = ShelveDB(radius)
    return world


def render_pseudo_graphic(name, res, turn):
    """Print pseudo graphic interface in console."""

    def rev_str(string):
        """Reverse word in string."""
        # ''.join([x for x in decor].reverse())
        return ''.join(reversed(list(string)))

    _line = '#' * 34 + (' TURN #%3d ' % turn) + '#' * 35
    decor = ',~~~ @ '
    letters = {'input_lc': 'Вы находитесь в локации :',
               'input_res': 'Кол-во ресурсов %d шт.'}
    # dec_name = '{0:{fill}{align}80}'.format((decor+name+rev_str(decor)),fill = ' ',align = '^')
    dec_name = (decor + name + rev_str(decor)).center(80)
    print(_line)
    print(letters['input_lc'])
    print(dec_name)
    print(letters['input_res'] % res)


def main():
    """"""
    db = generate_world(18)
    world = db.db
    sl = db.sl
    loc = ShelveLocation(world, sl, world[sl][1], world[sl][0])
    # loc = load_rand_locate()
    res, name = loc.resource, loc.name
    turn = 0
    while True:
        render_pseudo_graphic(name, res, turn)
        command = input("Сделать ход: ")
        if command in commands:
            if command == "exit":
                print('#' * 80)
                print(world.sync())
                sys.exit()
            # elif command == "randturn":
            #   loc = load_rand_locate()
            #   res, name = loc.resource, loc.name
            elif command == "p":
                get = loc.sub_resource()
                if get:
                    print("Вы собрали %d ресурса" % get)
                    res = loc.resource
                else:
                    print("Вы ничего не собрали")
            turn += 1  # Изменять шаг только после выполнения команды
        #  в противном случае мы ничего не сделали
        else:
            pass
        print('#' * 80)


if __name__ == '__main__':
    main()
