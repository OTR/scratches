"""
This example shows using classmethod as fabric method.
Page 148.
"""


class Pizza:
    def __init__(self, ingredients):
        self.ingredients = ingredients

    @classmethod
    def get_margherita(cls):
        return cls(["cheese", "tomato"])

    @classmethod
    def get_prosciutto(cls):
        return cls(["cheese", "tomato", "ham"])


if __name__ == '__main__':
    margherita = Pizza(["cheese", "tomato"])
    assert margherita.__dict__ == Pizza.get_margherita().__dict__
    prosciutto = Pizza(["cheese", "tomato", "ham"])
    assert prosciutto.__dict__ == Pizza.get_prosciutto().__dict__
