package com.example.simplecrud.integrationtest;

import com.example.simplecrud.dto.StudentRequest;
import com.example.simplecrud.model.Student;
import com.example.simplecrud.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Nowshad Hasan
 * @since 10/5/22 7:22 am
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext applicationContext;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    StudentService studentService;

    long studentId;

    @Test
    @DisplayName("Test - Create Student")
    public void createStudent() throws Exception {
        StudentRequest request = new StudentRequest();
        request.setName("Hasan");

        String jsonRequest = objectMapper.writeValueAsString(request);

        MvcResult result = mockMvc
                .perform(post("/student/add")
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        studentId = jsonObject.optLong("id");
        System.out.println(studentId);
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @DisplayName("Test - Get Single Student")
    public void GetSingleStudent() throws Exception {
        createStudent();
        MvcResult result = mockMvc
                .perform(get("/student/" + studentId))
                .andExpect(status().isOk()).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @DisplayName("Test - Get All Students")
    public void GetAllStudents() throws Exception {
        createStudent();
        MvcResult result = mockMvc
                .perform(get("/student/all"))
                .andExpect(status().isOk()).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @DisplayName("Test - Update Student")
    public void updateStudent() throws Exception {
        createStudent();
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setName("Nowshad");


        String jsonRequest = objectMapper.writeValueAsString(studentRequest);

        MvcResult result = mockMvc
                .perform(put("/student/" + studentId)
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @AfterEach
    public void deleteApt() {
        studentService.deleteStudent(studentId);
    }
}
