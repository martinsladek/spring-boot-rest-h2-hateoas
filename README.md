# Spring-eton
Spring application skeleton.
Application demonstrating basic Spring REST capabilities


## Intro
Spring application skeleton; implements:
* Spring Boot
* Spring Data JPA
* REST repositories
* CRUD services
* REST Controller
* H2 with JPA / Hibernate
* HATEOAS links


## Get, compile start

Clone application sources using http:
```
git clone https://github.com/martinsladek/spring-boot-rest-h2-hateoas.git
```

Clone using ssh:
```
git clone git@github.com:martinsladek/spring-boot-rest-h2-hateoas.git
```

Compile application:
```
cd spring-boot-rest-h2-hateoas
mvn clean install
```

Start application:
```
java -Dserver.port=8080 -jar target/springeton-1.0-SNAPSHOT.jar
```


## Configuration and monitoring
### H2 database

SQL console:
http://localhost:8080/h2-console
```
http://localhost:8080/h2-console
```

Initial schema and data:
```
resources/schema.sql
resources/data.sql
```

App config:
```
resources/application.properties
# Enable h2 console
spring.h2.console.enabled=true
# Show SQL queries:
spring.jpa.show-sql=true
```


## Client REST API
### Lesson

Get all lessons:
```
curl -s -i -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/lesson/all"
```

Get one lesson:
```
curl -s -i -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/lesson/1"
curl -s -i -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/lesson/2"
curl -s -i -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/lesson/3"
```

Create lesson:
```
curl -s -i -H "Connection: close" -H "Content-Type:application/json" -d '{"name":"directing"}' "http://localhost:8080/lesson"
```

Update lesson:
```
curl -s -i -H "Connection: close" -H "Content-Type:application/json" -X PUT -d '{"name":"editoring"}' "http://localhost:8080/lesson/4"
```

Delete lesson:
```
curl -s -i -H "Connection: close" -H "Content-Type:application/json" -X DELETE "http://localhost:8080/lesson/4"
```


### Lesson subscriptions

Lesson - list of subscribed students:
```
curl -s -i -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/lesson/1/student/all"
curl -s -i -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/lesson/2/student/all"
```

Subscribe student to lesson:
```
curl -s -i -H "Connection: close" -H "Content-type: application/json" -X PUT "http://localhost:8080/lesson/2/student/1"
curl -s -i -H "Connection: close" -H "Content-type: application/json" -X PUT "http://localhost:8080/lesson/1/student/2"
curl -s -i -H "Connection: close" -H "Content-type: application/json" -X PUT "http://localhost:8080/lesson/1/student/3"
curl -s -i -H "Connection: close" -H "Content-type: application/json" -X PUT "http://localhost:8080/lesson/1/student/4"
curl -s -i -H "Connection: close" -H "Content-type: application/json" -X PUT "http://localhost:8080/lesson/1/student/5"
curl -s -i -H "Connection: close" -H "Content-type: application/json" -X PUT "http://localhost:8080/lesson/1/student/5"
```

Unsubscribe student from lesson:
```
curl -s -i -H "Connection: close" -H "Content-type: application/json" -X DELETE "http://localhost:8080/lesson/2/student/1"
```


### Student

Get one student:
```
curl -s -i -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/student/1"
```

Get all students:
```
curl -s -i -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/student/all"
```

Create student:
```
curl -s -i -H "Connection: close" -H "Content-Type:application/json" -d '{"name":"Trinity","active":true,"credits":80}' "http://localhost:8080/student"
```

Update student:
```
curl -s -i -H "Connection: close" -H "Content-Type:application/json" -X PUT -d '{"id":1,"name":"Ada","active":false,"credits":5000}' "http://localhost:8080/student/1"
```

Delete student:
```
curl -s -i -H "Connection: close" -H "Content-Type:application/json" -X DELETE "http://localhost:8080/student/5"
```

Student subscriptions:
```
curl -s -i -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/student/1/lesson/all"
```

Curl params:
```
-i include output headers
-s suppress progress
Don't needed:
-v include input+output headers = too verbose
-X GET = default; not needed
-X POST implied by -d "data"; not needed
```
