// winston, morgan 연동
// 참고자료1 : https://levelup.gitconnected.com/better-logs-for-expressjs-using-winston-and-morgan-with-typescript-1c31c1ab9342
// 참고자료2 : https://xively.tistory.com/49
import morgan from "morgan";
import logger from "../configs/logger.js";

// Override the stream method by telling
// Morgan to use our custom logger instead of the console.log.
const stream = {
  write: (message) => {
    logger.http(message);
  },
};

const skip = () => {
  const env = process.env.NODE_ENV || "development";
  return env !== "development";
};

morgan.token("status", (req, res) => {
  let color;
  if (res.statusCode < 300) color = "\x1B[32m"; //green
  else if (res.statusCode < 400) color = "\x1B[36m"; //cyan
  else if (res.statusCode < 500) color = "\x1B[33m"; //yellow
  else if (res.statusCode < 600) color = "\x1B[31m"; //red
  else color = "\x1b[0m"; /*글자색 초기화*/

  return color + res.statusCode + "\x1b[35m" /*보라색*/;
});

morgan.token("request", (req) => {
  return "Request_" + JSON.stringify(req.body);
});

const morganMiddleware = morgan(
  "요청_:method | url_':url' | :request | Status_:status | 응답시간_:response-time ms (:res[content-length]줄)",
  { stream, skip }
);

export default morganMiddleware;
