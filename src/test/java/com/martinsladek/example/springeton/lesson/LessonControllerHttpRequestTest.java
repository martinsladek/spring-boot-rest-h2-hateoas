package com.martinsladek.example.springeton.lesson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LessonControllerHttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void subscribedStudentsShouldReturnSubscribedStudents() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/lesson/1/student",
                String.class)).contains("Adele");

        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/lesson/1/student",
                String.class)).contains("{\"id\":1,");
    }
}
