FROM node:18.16-alpine

RUN mkdir -p /app

WORKDIR /app

COPY package*.json /app

RUN npm install

COPY . /app

CMD ["npm", "start"]
