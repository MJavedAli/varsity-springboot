package com.stc.studentcourse.repository;

import com.stc.studentcourse.model.entity.Student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
        List<Student> findByRegistrationDateBetween(LocalDate start, LocalDate end);

}
