"""
This is some of my very first python programs
"""
import shelve
import sys
from random import randint, choice

__version__ = '0.0.3'
COMMANDS = {
    "n": "go to north",
    "s": "go to south",
    "w": "go to west",
    "e": "go to east",
    "p": "pick up resources",
    "exit": "exit from game",
    "rand_move": "make random move"
}

LOCATION_FIRST_NAME = [
    'Бурая', 'Смертельная', 'Счастливая', 'Дикая', 'Кровавая', 'Зеленая',
    'Верхняя', 'Заколдованная', 'Древняя'
]

LOCATION_SECOND_NAME = [
    'Роща', 'Поляна', 'Побережье', 'Овраг', 'Лес', 'Пустыня', 'Горы', 'Озеро',
    'Болото', 'Ущелье', 'Оазис'
]


class BasicLocation(object):
    """"""

    def __init__(self, resource=None, name=None):
        """"""
        self.resource = resource  # FIXME:
        if name:
            self.name = name
        else:
            self.name = 'Тестовая локация #%d' % randint(0, 255)

    def subtract_resource(self):
        """"""
        if self.resource:
            self.resource -= 1
            return 1
        else:
            return 0

    def add_resource(self):
        """Generate new resource."""
        raise NotImplementedError

    def link(self):
        """Link a location to another, make a portal."""
        raise NotImplementedError


class ShelveLocation(BasicLocation):
    """"""

    def __init__(self, link_db, location, resource=None, name=None):
        """"""
        BasicLocation.__init__(self, resource, name)
        self.link_db = link_db
        self.location = location

    def subtract_resource(self):
        """"""
        if self.resource:
            self.resource -= 1
            self.transfer()
            return 1
        else:
            return 0

    def transfer(self):
        """"""
        tmp = self.link_db[self.location]
        tmp[1] = self.resource
        self.link_db[self.location] = tmp


class ShelveDB(object):
    """"""

    def __init__(self, radius, base=None):
        """"""
        if base:
            self.storage = shelve.open(base)
        else:
            self.storage = shelve.open('./db.dat')
        self.radius = radius
        self.prefix = len(str(radius * 2))
        self.start_location = self.make_start_location()
        self.storage.sync()

    def make_dungeon(self):
        """Make a dungeon."""
        pass

    def make_start_location(self, start_location=None):
        """"""
        if start_location is None:
            _null = '0' * self.prefix
            start_location = '%s:%s' % (_null, _null)
        location_name = "Спуск в подземелье"
        resource_amount = randint(0, 255)
        # lw=None,ln=None,ls=None,le=None]
        # lw - link to west location etc
        self.storage[start_location] = [location_name, resource_amount]

        return start_location


def make_rand_location():
    """Для режима без карты вообще."""
    name = "%s %s" % (choice(LOCATION_FIRST_NAME), choice(LOCATION_SECOND_NAME))
    location = BasicLocation(resource=randint(0, 5), name=name)

    return location


def load_game_world_from_db(radius):
    """Try load base from a file."""
    try:
        world = ShelveDB(radius)
    except OSError as err:
        raise err
    else:
        return world


def render_pseudo_graphic(name, res, turn):
    """Print pseudo graphic interface in console."""
    title_line = '#' * 34 + (' TURN #%3d ' % turn) + '#' * 35
    decor = ',~~~ @ '
    reversed_decor = decor[::-1]
    location_description = {
        'curr_location': 'Вы находитесь в локации :',
        'curr_res_amount': 'Кол-во ресурсов %d шт.'
    }
    # decorated_location_name = '{0:{fill}{align}80}'.format(
    # (decor + name + rev_str(decor)), fill=' ', align='^')

    decorated_location_name = f"{decor}{name}{reversed_decor}".center(80)
    print(title_line)
    print(location_description['curr_location'])
    print(decorated_location_name)
    print(location_description['curr_res_amount'] % res)


def main():
    """"""
    shelve_object = load_game_world_from_db(18)
    storage = shelve_object.storage
    start_location = shelve_object.start_location
    location = ShelveLocation(
        storage,
        start_location,
        storage[start_location][1],
        storage[start_location][0]
    )
    # location = load_rand_location()
    res, name = location.resource, location.name
    turn = 0
    while True:
        render_pseudo_graphic(name, res, turn)
        command = input("Сделать ход: ")
        if command in COMMANDS:
            if command == "exit":
                print('#' * 80)
                storage.sync()
                sys.exit()
            # elif command == "rand_move":
            #   location = load_rand_locate()
            #   res, name = location.resource, location.name
            elif command == "p":
                is_picked = location.subtract_resource()
                if is_picked:
                    print("Вы собрали %d ресурса" % is_picked)
                    res = location.resource
                else:
                    print("Вы ничего не собрали")
            turn += 1  # Изменять шаг только после выполнения команды

        print('#' * 80)


if __name__ == '__main__':
    main()
