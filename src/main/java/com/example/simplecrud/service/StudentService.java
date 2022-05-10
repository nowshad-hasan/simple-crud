package com.example.simplecrud.service;

import com.example.simplecrud.dto.StudentRequest;
import com.example.simplecrud.model.Student;
import com.example.simplecrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Nowshad Hasan
 * @since 10/5/22 7:17 am
 */
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;


    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(long id) {
        return studentRepository.findById(id);
    }

    public Student save(StudentRequest request) {
        Student student = Student.builder().name(request.getName()).build();
        studentRepository.save(student);
        return student;
    }

    public boolean updateStudent(long id, StudentRequest request) {
        Optional<Student> optionalStudent = findById(id);
        if (optionalStudent.isPresent()) {
            final Student student = optionalStudent.get();
            student.setName(request.getName());
            studentRepository.save(student);
            return true;
        } else return false;
    }

    public boolean deleteStudent(long id) {
        Optional<Student> optionalStudent = findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.delete(optionalStudent.get());
            return true;
        } else return false;
    }
}
