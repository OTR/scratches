"""
This example shows the difference between class and instance attributes.
Page 140.
"""

class CountedObject:
    num_instances = 0

    def __init__(self):
        self.__class__.num_instances += 1


class BuggyCountedObject:
    num_instances = 0

    def __init__(self):
        self.num_instances += 1


class TypeCountedObject:
    num_instances = 0

    def __init__(self):
        type(self).num_instances += 1


if __name__ == '__main__':
    print(CountedObject.num_instances)
    print(CountedObject().num_instances)
    print(CountedObject().num_instances)
    print(CountedObject().num_instances)
    print("_" * 80)
    print(BuggyCountedObject.num_instances)
    print(BuggyCountedObject().num_instances)
    print(BuggyCountedObject().num_instances)
    print(BuggyCountedObject().num_instances)
    print("_" * 80)
    print(TypeCountedObject.num_instances)
    print(TypeCountedObject().num_instances)
    print(TypeCountedObject().num_instances)
    print(TypeCountedObject().num_instances)
