package com.thoughtworks.capability.gtb.entrancequiz.controller;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final List<Student> students = new ArrayList<Student>() {{
        add(new Student(1 , "成吉思汗"));
        add(new Student(2 , "鲁班七号"));
        add(new Student(3 , "太乙真人"));
        add(new Student(4 , "钟无艳"));
        add(new Student(5 , "花木兰"));
        add(new Student(6 , "雅典娜"));
        add(new Student(7 , "芈月"));
        add(new Student(8 , "白起"));
        add(new Student(9 , "刘禅"));
        add(new Student(10, "庄周"));
        add(new Student(11, "马超"));
        add(new Student(12, "刘备"));
        add(new Student(13, "哪吒"));
        add(new Student(14, "大乔"));
        add(new Student(15, "蔡文姬"));
    }};

    @Test
    void should_list_all_students_order_by_id_ascending() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        validateListStudentResult(resultActions, students);
    }

    void validateListStudentResult(ResultActions resultActions, List<Student> expected) throws Exception {
        Integer prevId = null;
        for (int i = 0; i < expected.size(); i++) {
            Student student = expected.get(i);
            Integer currId = student.getId();
            if (prevId != null) {
                assertTrue(currId > prevId);
            }
            prevId = currId;
            resultActions
                    .andExpect(jsonPath(String.format("$[%d].id"  , i), is(student.getId())))
                    .andExpect(jsonPath(String.format("$[%d].name", i), is(student.getName())));
        }
    }

}
