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
    public void shouldReturnSubscribedStudents() throws Exception {
        /* Check lessons */
        this.mockMvc.perform(get("/lesson/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1,\"name\":\"singing\"")));

        this.mockMvc.perform(get("/lesson/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2,\"name\":\"playing\"")));

        /* Check student-lesson subscriptions */
        this.mockMvc.perform(get("/lesson/1/student")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Adele")));

        this.mockMvc.perform(get("/lesson/2/student")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Freddy")));
    }

    @Test
    public void shouldReturnStudentSubscriptions() throws Exception {
        this.mockMvc.perform(get("/student/1/lesson")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("1")));

        this.mockMvc.perform(get("/student/2/lesson")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("2")));
    }

    @Test
    public void shouldReturnStudent() throws Exception {
        this.mockMvc.perform(get("/student/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Adele")));

        this.mockMvc.perform(get("/student/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Freddy")));

    }

    @Test
    public void shouldSubscribeStudent345ToLesson1AndCheckSortOrder() throws Exception {
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
    public void shouldSubscribeStudent1ToLessonSinging() throws Exception {
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
