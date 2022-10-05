package com.martinsladek.example.springeton.student;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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

    @Test
    public void shouldCreateAndDeleteStudent() throws Exception {
        /* check if new student is not yet registered */
        this.mockMvc.perform(get("/student")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(not(containsString("Trinity"))));

        /* create student */
        this.mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Trinity\",\"active\":true,\"credits\":80}")
                )
                .andDo(print()).andExpect(status().isCreated());

        /* check if student is newly created */
        this.mockMvc.perform(get("/student")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Trinity")));

        /* delete student */
        this.mockMvc.perform(delete("/student/6")).andDo(print()).andExpect(status().isNoContent());

        /* check if student is deleted */
        this.mockMvc.perform(get("/student")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(not(containsString("Trinity"))));
    }

    @Test
    public void shouldUpdateStudent() throws Exception {
        /* check if student is not yet updated */
        this.mockMvc.perform(get("/student/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(not(containsString("987"))));

        /* update student */
        this.mockMvc.perform(put("/student/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Adele\",\"active\":true,\"credits\":987}")
                )
                .andDo(print()).andExpect(status().isOk());

        /* check if student is newly created */
        this.mockMvc.perform(get("/student/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("987")));
    }
}
