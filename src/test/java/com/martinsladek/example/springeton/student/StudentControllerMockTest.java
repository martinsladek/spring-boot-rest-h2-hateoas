package com.martinsladek.example.springeton.student;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerMockTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnStudent() throws Exception {
        this.mockMvc.perform(get("/student/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Adele")));

        this.mockMvc.perform(get("/student/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Freddy")));

    }

    @Test
    public void shouldReturnStudentLessonSubscriptionsByStudent() throws Exception {
        this.mockMvc.perform(get("/student/1/lesson")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("1")));

        this.mockMvc.perform(get("/student/2/lesson")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("2")));
    }
}
