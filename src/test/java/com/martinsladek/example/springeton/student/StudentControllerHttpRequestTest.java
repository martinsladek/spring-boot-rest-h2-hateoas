package com.martinsladek.example.springeton.student;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentControllerHttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void subscribedStudentsShouldReturnSubscribedStudents() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/lesson/1/student/all",
                String.class)).contains("Adele");

        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/lesson/1/student/all",
                String.class)).contains("{\"id\":1,");

        assertThat(this.restTemplate.getForObject("http://localhost:" + port
                        + "/student/1/lesson/all",
                String.class)).contains("singing");
    }
}
