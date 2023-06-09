import datetime
import json
import logging
import sys
import time
from urllib.parse import urljoin

import requests
from apscheduler.schedulers.blocking import BlockingScheduler

from article_analyzer import ArticleAnalyzer
from articles import get_authors, parse_author_articles
from util import getenv

RETRY_COUNT = 3
RETRY_INTERVAL = 10  # seconds

sched = BlockingScheduler()


@sched.scheduled_job('cron', hour=0)
def job():
    start = time.time()

    authors = get_authors()
    joined_authors = '\n'.join([str(a) for a in authors])
    logging.info(f"authors: \n{joined_authors}")
    article_analyzer = ArticleAnalyzer()

    added_article_cnt = 0
    skipped_article_cnt = 0

    # parse RSS feed and add to DB for each author
    for author in authors:
        author_articles = parse_author_articles(author)
        for article in author_articles:
            logging.debug(f"start parsing - article: {article.get('title')}")

            content = article.get("summary")
            link_url = article.get("linkUrl")

            # skip duplicated articles
            is_duplicated = json.loads(
                requests.get(urljoin(getenv("API_URL"), f"/articles/exists?linkUrl={link_url}")).content)
            if is_duplicated:
                logging.debug(f"Skipping article: {article.get('title')}")
                skipped_article_cnt += 1
                continue

            left_cnt = RETRY_COUNT
            while left_cnt > 0:
                try:
                    article["hashtags"] = article_analyzer.extract_keywords(content)
                    break
                except Exception as e:
                    left_cnt -= 1
                    time.sleep(RETRY_INTERVAL)

            if left_cnt == 0:
                logging.error(f"Failed to extract keywords for article: {article.get('title')}")

            logging.debug(f"article: {article}")

            # POST request to API server
            url = urljoin(getenv("API_URL"), "/articles")
            res = requests.post(url, json=article)

            if res.ok:
                logging.info(f"Successfully added article: {article.get('title')}")
                added_article_cnt += 1
            else:
                logging.error(f"Error adding article: {article.get('title')}")

            logging.debug(f"finish parsing - article: {article.get('title')}")
            time.sleep(20)  # default sleep time

    end = time.time()
    result = datetime.timedelta(seconds=(end - start))
    logging.info(
        f"Total Job Running Time: {result}, "
        f"Added Article Count: {added_article_cnt}, "
        f"Skipped Article Count: {skipped_article_cnt}")


if __name__ == "__main__":
    # init logger
    logging.basicConfig(
        format="%(asctime)s %(levelname)s %(message)s",
        stream=sys.stdout,
        level=getenv("LOG_LEVEL", logging.INFO),  # Output level
    )

    sched.start()

    while True:
        time.sleep(1)
