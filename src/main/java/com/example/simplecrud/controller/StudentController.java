package com.example.simplecrud.controller;

import com.example.simplecrud.dto.StudentRequest;
import com.example.simplecrud.model.Student;
import com.example.simplecrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Nowshad Hasan
 * @since 9/5/22 7:09 am
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student getSingleStudent(@PathVariable("id") long id) {
        return studentService.findById(id).orElseThrow();
    }

    @PostMapping("/add")
    public Student addStudent(@Valid @RequestBody StudentRequest request) {
        Student student = studentService.save(request);
        return student;
    }

    @PutMapping("/{id}")
    public String updateStudent(@PathVariable("id") long id, @RequestBody @Valid StudentRequest request) {
        boolean isUpdated = studentService.updateStudent(id, request);
        if (isUpdated) {
            return "Successfully updated";
        } else {
            return "Not updated";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable("id") long id) {
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted) {
            return "Successfully deleted";
        } else return "Not deleted";
    }
}
