import json
import logging
import re
import time
from time import gmtime
from urllib.parse import urljoin

import feedparser
import requests
from bs4 import BeautifulSoup

from util import getenv

url_domain = getenv("API_URL")
log = logging.getLogger(__name__)
RSS_REGEXP = "(<([^>]+)>|\&nbsp;)"


def get_authors():
    url = urljoin(url_domain, "/authors/url")
    res = requests.get(url).content
    return json.loads(res)


def get_thumbnail_url(html: BeautifulSoup):
    img = html.find("img")
    if img is None:
        return None
    return img["src"]


def get_hashtags(entry):
    tags = entry.get("tags")
    if tags is None:
        return []
    return [tag["term"] for tag in tags]


def get_timestamp(entry):
    timestamp = entry.get("published_parsed")
    if timestamp is None:
        timestamp = entry.get("updated_parsed")
    if timestamp is None:
        timestamp = entry.get("created_parsed")
    if timestamp is None:
        timestamp = gmtime()

    return time.strftime("%Y-%m-%dT%H:%M:%S", timestamp)


def parse_author_articles(author):
    url = author["rssUrl"]
    entries = feedparser.parse(url).get("entries")
    articles = []

    for e in entries:

        # get HTML content
        content = e.get("content")[0]["value"] if e.get("content") else None
        if content is None:
            content = e.get("summary_detail")["value"] if e.get("summary_detail") else None
        if content is None:
            content = e.get("summary")

        # check if content is valid
        parsed_html = BeautifulSoup(content, "html.parser")
        is_valid_html = bool(parsed_html.find())
        if not is_valid_html:
            log.error(f"Invalid HTML content for entry: {e}")
            continue

        articles.append({
            "authorId": author["id"],
            "title": e.get("title"),
            "summary": re.sub(RSS_REGEXP, "", content)[:1000],
            "thumbnailUrl": get_thumbnail_url(parsed_html),
            "linkUrl": e.get("link"),
            "hashtags": get_hashtags(e),
            "timestamp": get_timestamp(e),
        })

    return articles
