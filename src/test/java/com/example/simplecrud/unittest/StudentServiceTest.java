package com.example.simplecrud.unittest;

import com.example.simplecrud.dto.StudentRequest;
import com.example.simplecrud.model.Student;
import com.example.simplecrud.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Nowshad Hasan
 * @since 10/5/22 7:44 am
 */
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    StudentService studentService;

    @Test
    public void testCreateStudent() {
        String name = "Nowshad";
        StudentRequest request = new StudentRequest(name);
        Student student = studentService.save(request);

        assertEquals(name, student.getName());
    }
}
