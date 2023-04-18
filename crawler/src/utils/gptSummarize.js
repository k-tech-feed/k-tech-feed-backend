import { Configuration, OpenAIApi } from "openai";
import dotenv from "dotenv";

dotenv.config();

const configuration = new Configuration({
  organization: process.env.ORGANIZATION,
  apiKey: process.env.API_KEY,
});

const openai = new OpenAIApi(configuration);

const messageGenerator = (content) => {
  return [
    {
      role: "user",
      content: `
      문장 : ${content}

      위의 문장을 바탕으로 요구사항1에 맞추어 요구사항2를 만족하는 결과물을 도출해줘.`,
    },
    {
      role: "system",
      content: `
        요구사항 1: 주어진 문장에서 핵심 키워드 3가지를 선정해서 결과에 넣어줘
        요구사항 2: 결과는 아래에서 명시한 결과형식과 반드시 똑같은 형태로 만들어줘
        요구사항 3: 결과 형식에 반드시 맞춰야해
        결과형식 :  { "keywords": ["핵심 키워드"] }`,
    },
  ];
};

const gptRequest = async (content) =>
  await openai.createChatCompletion({
    model: "gpt-3.5-turbo",
    temperature: 0.7,
    messages: messageGenerator(content),
  });

export { gptRequest };
