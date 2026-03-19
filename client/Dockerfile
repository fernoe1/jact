FROM node

EXPOSE 5000

WORKDIR /usr/src/app

COPY . .

ENV REACT_APP_API_URL=https://jactback.onrender.com

RUN npm install
RUN npm run build
RUN npm install -g serve

CMD [ "serve", "-s", "-l", "5000", "build" ]
