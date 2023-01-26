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


## Liquibase interaction
###Prepare Liquibase for schema generation:
resources/application.properties
```
# Disable deferring (do not postpone) data source initialization after Hibernate-based schema init:
spring.jpa.defer-datasource-initialization=false
# We do not need to touch:
#spring.sql.init.mode=never

# Enable persistence to file (for embedded database)
spring.datasource.url=jdbc:h2:C:/dev/spring/spring-boot-rest-h2-hateoas-db/h2data/h2;DB_CLOSE_ON_EXIT=FALSE

# Turn on schema initialization also on standalone (not embedded) databases
spring.sql.init.mode=always

# Turn on Hibernate schema update on application start
spring.jpa.hibernate.ddl-auto=update

# If necessary you can disable Liquibase in Spring Boot
#spring.liquibase.enabled=false
```

###Run app once to update schema in database:
```
java -jar target/springeton-1.0-SNAPSHOT.jar
```
Schema is updated.

###Create first changelog:
```
mvn liquibase:generateChangeLog
```
New changelog is created in path (defined in Maven pom Liquibase plugin configuration)
```
src/main/resources/db/changelog/generated/db.changelog-${maven.build.timestamp}.xml
```
Verify the changelog, (create diff against previous changelog, if any), add incremental changelog to 
```
src/main/resources/db/changelog/migration/db.changelog-${maven.build.timestamp}.xml
```

###Create incremental (diff) changelog:
Create database with previous/original state, save it to files h2-previous.mv.db, h2-previous.trace.db  
In liquibase.properties define database with previous/original state:
```
referenceUrl=jdbc:h2:C:/dev/spring/spring-boot-rest-h2-hateoas-db/h2data/h2-previous;DB_CLOSE_ON_EXIT=FALSE
referenceUsername=sa
referencePassword=
referenceDriver=org.h2.Driver
```
Run:
```
mvn liquibase:diff
```
New changelog is created in path (defined in Maven pom Liquibase plugin configuration)
```
src/main/resources/db/changelog/generated/db.changelog-${maven.build.timestamp}.xml
```
Verify the changelog, add incremental changelog to:
```
src/main/resources/db/changelog/migration/db.changelog-${maven.build.timestamp}.xml
```

###Apply changelog:
New changelog will be applied when the application starts.  <br />
To apply changelog manually run:
```
mvn liquibase:update
```
To generate SQL from changelog run:
```
mvn liquibase:updateSQL
```
Result will be saved into:
```
target/liquibase/migrate.sql
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
