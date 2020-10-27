package com.thoughtworks.capability.gtb.entrancequiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Group;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Order(1)
    void should_update_groups() throws Exception {
        mockMvc.perform(patch("/groups"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                    Group[] actual = objectMapper.readValue(content, Group[].class);
                    validateListGroupResult(actual);
                });
    }

    @Test
    @Order(2)
    void should_list_all_groups_order_by_id_ascending() throws Exception {
        mockMvc.perform(get("/groups"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                    Group[] actual = objectMapper.readValue(content, Group[].class);
                    validateListGroupResult(actual);
                });
    }

    void validateListGroupResult(Group[] actual) {
        Integer prevId = null;
        for (int i = 0; i < 6; i++) {
            Group actualGroup = actual[i];
            Integer currId = actualGroup.getId();
            if (prevId != null) {
                assertTrue(currId > prevId);
            }
            prevId = currId;
            assertEquals(i + 1, actualGroup.getId());
        }
    }

}
