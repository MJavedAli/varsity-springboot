package com.stc.studentcourse.repository;

import com.stc.studentcourse.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
