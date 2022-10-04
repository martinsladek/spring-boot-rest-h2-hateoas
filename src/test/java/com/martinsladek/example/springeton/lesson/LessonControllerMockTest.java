package com.martinsladek.example.springeton.lesson;

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
public class LessonControllerMockTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnLesson() throws Exception {
        /* Check lessons */
        this.mockMvc.perform(get("/lesson/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1,\"name\":\"singing\"")));

        this.mockMvc.perform(get("/lesson/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2,\"name\":\"playing\"")));
    }

    @Test
    public void shouldReturnStudentLessonSubscriptionByLesson() throws Exception {
        /* Check student-lesson subscriptions */
        this.mockMvc.perform(get("/lesson/1/student")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Adele")));

        this.mockMvc.perform(get("/lesson/2/student")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Freddy")));
    }
}
