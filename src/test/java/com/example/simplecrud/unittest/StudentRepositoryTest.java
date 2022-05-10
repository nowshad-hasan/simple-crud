package com.example.simplecrud.unittest;

import com.example.simplecrud.model.Student;
import com.example.simplecrud.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Nowshad Hasan
 * @since 10/5/22 8:27 am
 */
@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    StudentRepository studentRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(studentRepository).isNotNull();
    }

    @Test
    public void saveStudent() {
        final String name = "Rafi";
        Student student = Student.builder().name(name).build();

        studentRepository.save(student);
        Student savedStudent = studentRepository.findByName(name).orElseThrow(null);
        assertEquals(student.getName(), savedStudent.getName());
    }

    @Test
    public void getAllUsers() {
        Student student1 = Student.builder().name("Summit").build();
        Student student2 = Student.builder().name("Haque").build();

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        studentRepository.saveAll(students);
        List<Student> savedUsers = studentRepository.findAll();
        assertEquals(students.size(), savedUsers.size());
    }

    @Test
    public void deleteUser() {
        final String name = "Moqsad";
        Student student = Student.builder().name(name).build();

        studentRepository.save(student);
        studentRepository.delete(student);
        Student deletedUser = studentRepository.findByName(name).orElse(null);
        assertNull(deletedUser);
    }

    @Test
    public void updateUser() {
        String name = "Rakhi";
        String updatedName = "Ahmed";
        Student student = Student.builder().name(name).build();

        studentRepository.save(student);
        student.setName(updatedName);
        studentRepository.save(student);
        assertEquals(updatedName, student.getName());
    }
}
