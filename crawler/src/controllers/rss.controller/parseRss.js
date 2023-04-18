import Parser from "rss-parser";
import { gptRequest } from "../../utils/gptSummarize.js";

const parser = new Parser();

const RSS_REGEXP = /(<([^>]+)>|\&nbsp;)/gi;

const fetchFeed = async (id, url) => {
  return { ...(await parser.parseURL(url)), id };
};

const summarizeFeedItem = async (feedItem) => {
  const content = feedItem.content;

  try {
    const gptResponse = await gptRequest(content);
    const summarizedContent = gptResponse.data.choices[0].message.content;

    return {
      ...feedItem,
      content: JSON.parse(JSON.stringify(summarizedContent)),
    };
  } catch (err) {
    console.log("gpt 에러 발생! : ", err);
    return {
      ...feedItem,
    };
  }
};

const parseRss = async (req, res) => {
  const rssRequests = JSON.parse(JSON.stringify(req.body));

  // feed불러오기
  const feeds = await Promise.all(rssRequests.map(async ({ id, rssUrl }) => fetchFeed(id, rssUrl)));

  // 파싱 후 아티클들의 배열로 만들기
  const feedItems = JSON.parse(JSON.stringify(feeds))
    .map((feed) => {
      return feed.items.map((item) => {
        return {
          id: feed.id,
          title: item.title,
          content: item.content.replace(RSS_REGEXP, "").slice(0, 1000),
          pubDate: item.pubDate,
          categories: item.categories,
          link: item.link,
        };
      });
    })
    .flat();

  for (let i = 0; i < feedItems.length; i++) {
    setTimeout(async () => {
      const summarized = await summarizeFeedItem(feedItems[i]);
      // summarized로 api 요청 보내기
      console.log("summarize : ", summarized.content);
    }, 20000 * i);
  }

  //   const summarized = await summarizeFeedItem(feedItems[0]);
  //   console.log(summarized);

  res.status(200).json({
    ...feedItems,
  });
};

export default parseRss;
