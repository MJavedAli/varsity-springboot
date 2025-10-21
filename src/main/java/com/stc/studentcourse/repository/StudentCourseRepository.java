package com.stc.studentcourse.repository;

import com.stc.studentcourse.model.entity.Student;
import com.stc.studentcourse.model.entity.StudentCourse;
import com.stc.studentcourse.model.entity.StudentCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseKey> {
    
    long countByStudent(Student student);
    // List<StudentCourse> findByIdStudentId(Student student);
List<StudentCourse> findByIdStudentId(String studentId);


}
