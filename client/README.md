# Run locally
set .env REACT_APP_API_URL (backend url)

dev
```
npm install
npm run start
```
prod
```
npm build
```

# Docker
change ENV REACT_APP_API_URL to your backend url.

build image
```
docker build -t <whatevername> .
```
run container
```
docker run -d -p 5000:5000 <whatevername>
```
run container at custom port
```
docker run -d -p <whateverport1>:<whateverport2> <whatevername> serve -s -l <whateverport2> build
