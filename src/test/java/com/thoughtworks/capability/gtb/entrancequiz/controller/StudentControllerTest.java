package com.thoughtworks.capability.gtb.entrancequiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Student;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

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
    @Order(1)
    void should_list_all_students_order_by_id_ascending() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                    Student[] deserialized = objectMapper.readValue(content, Student[].class);
                    validateListStudentResult(students, deserialized);
                });
    }

    void validateListStudentResult(List<Student> expected, Student[] actual) {
        Integer prevId = null;
        for (int i = 0; i < expected.size(); i++) {
            Student expectedStudent = expected.get(i);
            Student actualStudent = actual[i];
            Integer currId = actualStudent.getId();
            if (prevId != null) {
                assertTrue(currId > prevId);
            }
            prevId = currId;
            assertEquals(expectedStudent, actualStudent);
        }
    }

    @Test
    @Order(2)
    void should_add_student() throws Exception {
        Student newStudent = new Student();
        newStudent.setName("小乔");
        String serialized = objectMapper.writeValueAsString(newStudent);
        mockMvc.perform(post("/student")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(serialized))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                    Student deserialized = objectMapper.readValue(content, Student.class);
                    assertEquals(newStudent.getName(), deserialized.getName());
                });
    }

    @Test
    @Order(3)
    void should_add_student_with_existing_name() throws Exception {
        Student newStudent = new Student();
        newStudent.setName(students.get(0).getName());
        String serialized = objectMapper.writeValueAsString(newStudent);
        mockMvc.perform(post("/student")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(serialized))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                    Student deserialized = objectMapper.readValue(content, Student.class);
                    assertEquals(newStudent.getName(), deserialized.getName());
                });
    }

    @Test
    @Order(4)
    void should_receive_error_when_add_student_given_null_name() throws Exception {
        Student newStudent = new Student();
        String serialized = objectMapper.writeValueAsString(newStudent);
        mockMvc.perform(post("/student")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(serialized))
                .andExpect(status().isBadRequest());
    }

}
