package com.martinsladek.example.springeton;

import com.martinsladek.example.springeton.student.Student;
import com.martinsladek.example.springeton.student.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(StudentRepository repository) {

        return args -> {
//            log.info("Preloading " + repository.save(new Student("Karel", true, 10)));
//            log.info("Preloading " + repository.save(new Student("Pepa", true, 20)));
        };
    }
}