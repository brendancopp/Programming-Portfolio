class Vector2D(object):

    def __init__(self, x=0.0, y=0.0):
        self.x = x
        self.y = y

    @classmethod
    def fromTuple(cls, tuple):
        if len(tuple) == 2:
            return cls(tuple[0], tuple[1])
        else:
            ValueError("Invalid tuple length. Expected length of 2.")

    def __iter__(self):
        """Returns (x,y)"""
        yield self.x
        yield self.y

class Box(object):

    def __init__(self, left = 0.0, up = 0.0, right= 0.0, down = 0.0):
        self.left = left
        self.up = up
        self.right = right
        self.down = down

    @classmethod
    def fromTuple(cls, tuple):
        if len(tuple) == 4:
            return cls(tuple[0], tuple[1], tuple[2], tuple[3])
        else:
            raise ValueError("Invalid tuple length. Expected length of 4.")

    def __iter__(self):
        yield self.left
        yield self.up
        yield self.right
        yield self.down

    def width(self):
        return self.right - self.left

    def height(self):
        return self.down - self.up

    def size(self):
        return (self.width(), self.height())

    def location(self):
        return (self.left, self.up)

    def get_left(self):
        return self.left

    def get_right(self):
        return self.right



