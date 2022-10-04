package com.martinsladek.example.springeton.studentlesson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentLessonControllerMockTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldSubscribeStudent345ToLesson1AndCheckSuccess() throws Exception {
        /* subscribe student 3, 4, 5 to specific lesson */
        this.mockMvc.perform(put("/lesson/1/student/3")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(put("/lesson/1/student/4")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(put("/lesson/1/student/5")).andDo(print()).andExpect(status().isOk());

        /* check if student is newly subscribed to lesson */
        this.mockMvc.perform(get("/lesson/1/student")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.studentList[0].name", anyOf(is("Adele"), is("Freddy"), is("John"), is("Michael"), is("Steve"))))
                .andExpect(jsonPath("$._embedded.studentList[1].name", anyOf(is("Adele"), is("Freddy"), is("John"), is("Michael"), is("Steve"))))
                .andExpect(jsonPath("$._embedded.studentList[2].name", anyOf(is("Adele"), is("Freddy"), is("John"), is("Michael"), is("Steve"))))
                .andExpect(jsonPath("$._embedded.studentList[3].name", anyOf(is("Adele"), is("Freddy"), is("John"), is("Michael"), is("Steve"))))
        ;
    }

    @Test
    public void shouldSubscribeStudent2ToLesson1Singing() throws Exception {
        /* check if student is not yet subscribed to lesson */
        this.mockMvc.perform(get("/student/2/lesson")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(not(containsString("singing"))));

        /* subscribe student 1 to specific lesson */
        this.mockMvc.perform(put("/lesson/1/student/2")).andDo(print()).andExpect(status().isOk());

        /* Repeated subscription causes error */
        this.mockMvc.perform(put("/lesson/1/student/2")).andDo(print()).andExpect(status().isConflict());

        /* check if student is newly subscribed to lesson */
        this.mockMvc.perform(get("/student/2/lesson")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("singing")));
    }
}
