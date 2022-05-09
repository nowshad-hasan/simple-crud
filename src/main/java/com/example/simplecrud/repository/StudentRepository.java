package com.example.simplecrud.repository;

import com.example.simplecrud.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nowshad Hasan
 * @since 9/5/22 7:11 am
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
