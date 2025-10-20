package com.stc.studentcourse.repository;

import com.stc.studentcourse.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}
