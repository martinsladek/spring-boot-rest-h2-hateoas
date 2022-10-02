package com.martinsladek.example.springeton;

import static org.assertj.core.api.Assertions.assertThat;

import com.martinsladek.example.springeton.student.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Autowired
	StudentController studentController;

	@Test
	void contextLoads() {
		assertThat(studentController).isNotNull();
	}

}
