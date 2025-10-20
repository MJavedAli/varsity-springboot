package com.stc.studentcourse.repository;

import com.stc.studentcourse.model.entity.StudentCourse;
import com.stc.studentcourse.model.entity.StudentCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseKey> {
}
