import Parser from "rss-parser";
import { parse } from 'node-html-parser';
import { gptRequest } from "../../utils/gptSummarize.js";

const parser = new Parser();

const RSS_REGEXP = /(<([^>]+)>|\&nbsp;)/gi;

// feed 크롤링
const fetchFeed = async (id, url) => {
  return { ...(await parser.parseURL(url)), id };
};

// hashtag 추가
const addHashtags = async (feedItem) => {
  const summary = feedItem.summary;
  try {
    const gptResponse = await gptRequest(summary);
    const gptContent = gptResponse.data.choices[0].message.content;
    const hashtags = JSON.parse(gptContent).keywords;

    return {
      ...feedItem,
      hashtags,
    };
  } catch (err) {
    console.log("gpt 에러 발생! : ", err);
    return {
      ...feedItem,
      hashtags: feedItem.categories,
    };
  }
};

// 썸네일 이미지 추출
const getThumbnailImg = (content) => {
  const domRoot = parse(content);
  const allImgNodes = domRoot.querySelectorAll('img');
  const imgNodes = allImgNodes ? domRoot.querySelectorAll('img')?.filter(({attributes}) => !attributes.src.includes('emoji')) : null;
  return imgNodes.length > 0 ? imgNodes[0].attributes.src : '';
}

const parseRss = async (req, res) => {
  const rssRequests = JSON.parse(JSON.stringify(req.body));

  // feed불러오기
  const feeds = await Promise.all(rssRequests.map(async ({ id, rssUrl }) => fetchFeed(id, rssUrl)));
  
  // 파싱 후 아티클들의 배열로 만들기
  const feedItems = JSON.parse(JSON.stringify(feeds))
    .map((feed) => {
      return feed.items.map((item) => {
        const content = item.hasOwnProperty('content:encoded') ? item['content:encoded'] : item.content;
        const domRoot = parse(content);
        const allImgNodes = domRoot.querySelectorAll('img');
        const imgNodes = allImgNodes ? domRoot.querySelectorAll('img')?.filter(({attributes}) => !attributes.src.includes('emoji')) : null;

        return {
          authorId: feed.id,
          title: item.title,
          summary: content.replace(RSS_REGEXP, "").slice(0, 1000),
          thumbnailUrl: getThumbnailImg(content),
          linkUrl: item.link,
          categories: item.categories,
          timestamp: item.pubDate,
        };
      });
    })
    .flat();

  for (let i = 0; i < feedItems.length; i++) {
    setTimeout(async () => {
      // hashtag 추가된 결과물
      const newContent = await addHashtags(feedItems[i]);
      // summarized로 api 요청 보내기
      console.log("newContent : ", newContent);
    }, 20000 * i);
  }


  res.status(200).json({
    ...feedItems,
  });
};

export default parseRss;
