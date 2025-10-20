package com.stc.studentcourse.controller;

import com.stc.studentcourse.model.entity.Course;
import com.stc.studentcourse.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{courseCode}")
    public ResponseEntity<Course> getCourseById(@PathVariable String courseCode) {
        return courseService.getCourseById(courseCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PutMapping("/{courseCode}")
    public ResponseEntity<Course> updateCourse(@PathVariable String courseCode, @RequestBody Course course) {
        return courseService.getCourseById(courseCode)
                .map(existing -> {
                    course.setCourseCode(courseCode);
                    return ResponseEntity.ok(courseService.saveCourse(course));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{courseCode}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String courseCode) {
        courseService.deleteCourse(courseCode);
        return ResponseEntity.noContent().build();
    }
}
