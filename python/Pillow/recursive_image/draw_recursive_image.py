"""

"""
from pathlib import Path

from PIL import Image


BASE_DIR = Path(__file__).parent
PATH_TO_SOURCE_IMG = BASE_DIR / "source.png"
PATH_TO_RESULT_IMG = BASE_DIR / "result.png"


def draw_recursive_image(path: Path) -> None:
    """"""
    source_img = Image.open(path)
    sub_img_height, sub_img_width = source_img.size
    result_img = Image.new("RGB", (sub_img_height, sub_img_width))
    recursion_depth = 1
    while sub_img_height >= 1 and sub_img_width >= 1:
        source_img = source_img.resize((sub_img_height, sub_img_width))
        result_img.paste(source_img, (sub_img_height, sub_img_width))
        for sub_img in range((2 ** recursion_depth) - 1):
            new_vertical_sub_img_coords = (
                sub_img_height + sub_img_height * sub_img,
                sub_img_width
            )
            result_img.paste(source_img, new_vertical_sub_img_coords)
            new_horizontal_sub_img_coords = (
                sub_img_height,
                sub_img_width + sub_img_width * sub_img
            )
            result_img.paste(source_img, new_horizontal_sub_img_coords)

        recursion_depth += 1
        sub_img_height = sub_img_height >> 1
        sub_img_width = sub_img_width >> 1

    result_img.save(PATH_TO_RESULT_IMG)


if __name__ == '__main__':
    draw_recursive_image(PATH_TO_SOURCE_IMG)
