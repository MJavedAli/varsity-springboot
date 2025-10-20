package com.stc.studentcourse.controller;

import com.stc.studentcourse.model.entity.Course;
import com.stc.studentcourse.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final RegistrationService registrationService;

    public CourseController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // ------------------- Register Course -------------------
    @PostMapping("/register")
    public Map<String, Object> registerCourse(@RequestBody Course course) {
        return registrationService.registerCourse(course);
    }

    // ------------------- Get All Courses -------------------
    @GetMapping
    public List<Course> getAllCourses() {
        return registrationService.getAllCourses();
    }
}
