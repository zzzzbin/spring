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

## Transaction:

- Isolation(independence) level:
  + READ_UNCOMMITTED: you can read data that is not committed by other transactions. 
  If it is rolled back, invalid data is searched.
  + READ_COMMITTED: data that has not been committed by other transactions cannot be read
  + REPEATABLE_READ: if data is read multiple times within a transaction, the same value is read
  even if other transaction changed data in the middle
  + SERIALIZABLE: process transactions one by one.
  => 3 problems:
  + Dirty read: read the data before commit
  + Non-repeatable read: read the same data many times in a transaction, at the same time, another transaction
   update the data => data change will be change in the middle
  + Phantom read: read the same data many times in a transaction. At the same time another transaction
  adds new data => number of records to be processed will be increased in the middle of transaction.
 - Propagation level: it determines whether or not a new transaction is created and started.
 
 ## AOP terminology
 
 - Advice: it is the processing content to be executed in AOP
 - Pointcut: the object(class or method) on which the operation is to be performed.
 - JoinPoint: the timing at which processing is executed.
 - JoinPoint time: before, after, afterReturning, around, afterThrowing (compare with Advice). 
 (call the method => return or exception) https://blog.espenberntsen.net/2010/03/20/aspectj-cheat-sheet/
 
 ## Exception handling: 3 ways
 
  - in the @AfterThrowing aspect
  - for each controller class
  - for entire webapp
 