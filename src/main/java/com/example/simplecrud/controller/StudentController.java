package com.example.simplecrud.controller;

import com.example.simplecrud.dto.StudentRequest;
import com.example.simplecrud.model.Student;
import com.example.simplecrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Nowshad Hasan
 * @since 9/5/22 7:09 am
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    StudentRepository repository;

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Student getSingleStudent(@PathVariable("id") long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping("/add/{id}")
    public Student addStudent(@PathVariable("id") long id, @Valid StudentRequest request) {
        Student student = Student.builder().name(request.getName()).build();
        repository.save(student);
        return student;
    }

    @PostMapping("/update/{id}")
    public void updateStudent(@PathVariable("id") long id, @Valid StudentRequest request) {
        Optional<Student> optionalStudent = repository.findById(id);
        if (optionalStudent.isPresent()) {
            final Student student = optionalStudent.get();
            student.setName(request.getName());
            repository.save(student);
        } else {
            throw new IllegalArgumentException("No student found");
        }
    }

    @GetMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable("id") long id) {
        Optional<Student> optionalStudent = repository.findById(id);
        optionalStudent.ifPresent(student -> repository.delete(student));
    }
}
