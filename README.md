# Spring-eton
CRUD application skeleton demonstrating basic repository operations


## Intro
Spring application skeleton; implements:
* Spring Boot
* Spring Data JPA
* REST repositories
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
# git clone git@github.com:martinsladek/spring-boot-rest-h2-hateoas.git
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


## Client REST API
### Lesson

Get all lessons:
```
curl -v -H "Connection: close" --header "Content-type: application/json" --request GET "http://localhost:8080/lesson/all"
```

Check individual lesson:
```
curl -v -H "Connection: close" --header "Content-type: application/json" --request GET "http://localhost:8080/lesson/1"
curl -v -H "Connection: close" --header "Content-type: application/json" --request GET "http://localhost:8080/lesson/2"
curl -v -H "Connection: close" --header "Content-type: application/json" --request GET "http://localhost:8080/lesson/3"
```

### Lesson subscriptions

Lesson - list of subscribed students:
```
curl -v -H "Connection: close" --header "Content-type: application/json" --request GET "http://localhost:8080/lesson/1/student/all"
curl -v -H "Connection: close" --header "Content-type: application/json" --request GET "http://localhost:8080/lesson/2/student/all"
```

Subscribe student to lesson:
```
curl -v -H "Connection: close" --header "Content-type: application/json" --request PUT "http://localhost:8080/lesson/2/student/1"
curl -v -H "Connection: close" --header "Content-type: application/json" --request PUT "http://localhost:8080/lesson/1/student/3"
curl -v -H "Connection: close" --header "Content-type: application/json" --request PUT "http://localhost:8080/lesson/1/student/4"
curl -v -H "Connection: close" --header "Content-type: application/json" --request PUT "http://localhost:8080/lesson/1/student/5"
curl -v -H "Connection: close" --header "Content-type: application/json" --request PUT "http://localhost:8080/lesson/1/student/5"
```


### Student

Display all students:
```
curl -v -H "Connection: close" --header "Content-type: application/json" --request GET "http://localhost:8080/student/all"
```

Display individual student:
```
curl -v -H "Connection: close" --header "Content-type: application/json" --request GET "http://localhost:8080/student/1"
```

Student subscriptions:
```
curl -v -H "Connection: close" --header "Content-type: application/json" --request GET "http://localhost:8080/student/1/lesson/all"
```
