# Run locally
set .env
```
PORT=
MONGO_URI=
SECRET=
SMTP_GMAIL=
SMTP_GMAIL_PASS=
```
run
```
npm install
node server
```

# Docker
build image 
```
docker build -t <whatevername> .
```
run container
```
docker run -d -p <whateverport1>:<whateverport2> -e PORT=<whateverport2> -e MONGO_URI= -e SECRET= -e SMTP_GMAIL= -e SMTP_GMAIL_PASS <whatevername>
```
