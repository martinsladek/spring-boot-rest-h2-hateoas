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

###App config:
resources/application.properties
```

# Enable h2 console
spring.h2.console.enabled=true

# Show SQL queries:
spring.jpa.show-sql=true

# Hibernate-generated DB tables (one of following + do not defer):
spring.jpa.defer-datasource-initialization=false
spring.jpa.hibernate.ddl-auto=create

# OR:
spring.jpa.defer-datasource-initialization=false
spring.jpa.hibernate.hbm2ddl.auto=create

# use data.sql after Hibernate-based generation:
spring.jpa.defer-datasource-initialization=true
```


## Client REST API
### Lesson

Get all lessons:
```
curl -s -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/lesson" | json_pp
curl -s -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/lesson" | json_pp
```

Get one lesson:
```
curl -s -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/lesson/1" | json_pp
curl -s -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/lesson/2" | json_pp
curl -s -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/lesson/3" | json_pp
```

Create lesson:
```
curl -s -H "Connection: close" -H "Content-Type:application/json" -d '{"name":"directing"}' "http://localhost:8080/lesson" | json_pp
```

Update lesson:
```
curl -s -H "Connection: close" -H "Content-Type:application/json" -X PUT -d '{"name":"editoring"}' "http://localhost:8080/lesson/4" | json_pp
```

Delete lesson:
```
curl -s -H "Connection: close" -H "Content-Type:application/json" -X DELETE "http://localhost:8080/lesson/4" | json_pp
```


### Student-Lesson subscriptions

List Student to Lesson subscriptions by Lesson:
```
curl -s -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/lesson/1/student" | json_pp
curl -s -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/lesson/2/student" | json_pp
```

Subscribe Student to Lesson:
```
curl -s -H "Connection: close" -H "Content-type: application/json" -X PUT "http://localhost:8080/lesson/2/student/1" | json_pp
curl -s -H "Connection: close" -H "Content-type: application/json" -X PUT "http://localhost:8080/lesson/1/student/2" | json_pp
curl -s -H "Connection: close" -H "Content-type: application/json" -X PUT "http://localhost:8080/lesson/1/student/3" | json_pp
curl -s -H "Connection: close" -H "Content-type: application/json" -X PUT "http://localhost:8080/lesson/1/student/4" | json_pp
curl -s -H "Connection: close" -H "Content-type: application/json" -X PUT "http://localhost:8080/lesson/1/student/5" | json_pp
curl -s -H "Connection: close" -H "Content-type: application/json" -X PUT "http://localhost:8080/lesson/1/student/5" | json_pp
```

Unsubscribe Student from Lesson:
```
curl -s -H "Connection: close" -H "Content-type: application/json" -X DELETE "http://localhost:8080/lesson/2/student/1" | json_pp
```


### Student

Get one student:
```
curl -s -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/student/1" | json_pp
```

Get all students:
```
curl -s -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/student" | json_pp
```

Create student:
```
curl -s -H "Connection: close" -H "Content-Type:application/json" -d '{"name":"Trinity","active":true,"credits":80}' "http://localhost:8080/student" | json_pp
```

Update student:
```
curl -s -H "Connection: close" -H "Content-Type:application/json" -X PUT -d '{"id":1,"name":"Ada","active":false,"credits":5000}' "http://localhost:8080/student/1" | json_pp
```

Delete student:
```
curl -s -H "Connection: close" -H "Content-Type:application/json" -X DELETE "http://localhost:8080/student/5" | json_pp
```

List Student to Lesson subscriptions by Student:
```
curl -s -H "Connection: close" -H "Content-type: application/json" "http://localhost:8080/student/1/lesson" | json_pp
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

Format JSON output:
```
curl -s ... | json_pp
```
