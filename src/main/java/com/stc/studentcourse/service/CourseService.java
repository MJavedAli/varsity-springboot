package com.stc.studentcourse.service;

import com.stc.studentcourse.model.entity.Course;
import com.stc.studentcourse.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(String courseCode) {
        return courseRepository.findById(courseCode);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(String courseCode) {
        courseRepository.deleteById(courseCode);
    }
}
