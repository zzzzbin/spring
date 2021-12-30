# Getting Started

### docker commands
```
- docker run -d --platform linux/x86_64 -p 3307:3306 --name mysqldb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=test mysql
- docker logs mysqldb
- docker build -t app .
- docker run -p 9090:8080 --name app -e MYSQL_HOST=mysqldb -e MYSQL_USER=root -e MYSQL_PASSWORD=root -e MYSQL_PORT=3306 app
- docker network create spring-net
- docker network connect --help
- docker network connect spring-net mysqldb
- docker container inspect mysqldb
- docker run -p 9090:8080 --name app --net spring-net -e MYSQL_HOST=mysqldb -e MYSQL_USER=root -e MYSQL_PASSWORD=root -e MYSQL_PORT=3306 app
- docker-compose up --build
- docker-compose down
```
 
## app url
```
 - http://localhost:9090/user/list
```
## ref
https://www.youtube.com/watch?v=PAQvxqocb6A&ab_channel=CodingBoot