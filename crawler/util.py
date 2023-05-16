import os

from dotenv import load_dotenv

load_dotenv()


def getenv(key, default=None):
    var = os.getenv(key, default)
    if var is None:
        raise Exception(f"'{key}' does not exist")
    return var
