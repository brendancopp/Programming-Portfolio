from PIL import Image
from pytesseract import image_to_string
import os

path = '20161533487.tif'

#Returns string of image text with given crop area
def get_text_from_image(image, crop_box):
    cropped_image = image.crop(crop_box)
    return image_to_string(cropped_image)


def normalize_value(value, max_value):
    """Normalize a value using given max value

    Args:
        value(int): value to be normalized
        max_value(int): maximum value used to normalize

    Returns:
        float: value between 0 and 1

    Raises:
        ValueError: If value > max_value
    """
    if value < max_value:
        return float(value) / float(max_value)
    else:
        raise ValueError("Value cannot be greater than max value")

def from_normal(normalized_value, max_value):
    """Projects normalized value onto integer scale using max value

        Args:
            normalized_value(int): normalized value to project
            max_value(int): max value to scale to

        Returns:
            int: normalized

        Raises:
            ValueError: If normalized_value is not between 0 and 1
    """
    if 0 <= normalized_value <= 1:
        return int(round(normalized_value * max_value))
    else:
        raise ValueError("Normalized value must be between 0 and 1")


def normalize_pixel_location(pixel_xy, resolution):
    pixel_x = normalize_value(pixel_xy[0], resolution[0])
    pixel_y = normalize_value(pixel_xy[1], resolution[1])
    return (pixel_x, pixel_y)


def get_pixel_from_normalized(normalized_xy, resolution):
    pixel_x = from_normal(normalized_xy[0], resolution[0])
    pixel_y = from_normal(normalized_xy[1], resolution[1])
    return (pixel_x, pixel_y)

def normalize_box(box, resolution):
    left = normalize_value(box[0], resolution[0])
    top = normalize_value(box[1], resolution[1])
    right = normalize_value(box[2], resolution[0])
    bottom = normalize_value(box[3], resolution[1])


