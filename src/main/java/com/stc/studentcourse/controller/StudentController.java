package com.stc.studentcourse.controller;

import com.stc.studentcourse.model.entity.Student;
import com.stc.studentcourse.service.RegistrationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final RegistrationService registrationService;

    public StudentController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // ------------------- Register Student -------------------
    @PostMapping("/register")
    public Map<String, Object> registerStudent(@RequestBody Student student) {
        return registrationService.registerStudent(student);
    }

    // ------------------- Get Student by ID / National ID / Full Name -------------------
    @GetMapping("/profile")
    public Optional<Student> getStudentProfile(
            @RequestParam(required = false) String universityId,
            @RequestParam(required = false) String nationalId,
            @RequestParam(required = false) String fullName
    ) {
        return registrationService.getStudentProfileById(universityId, nationalId, fullName);
    }

    // ------------------- Get Students by Registration Date Range -------------------
    @GetMapping("/profile/date-range")
    public List<Student> getStudentProfileDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return registrationService.getStudentProfileDateRange(from, to);
    }

    // ------------------- Get Student Course Details -------------------
    @GetMapping("/{studentId}/courses")
    public Map<String, Object> getStudentCourseDetails(@PathVariable String studentId) {
        return registrationService.getStudentCourseDetails(studentId);
    }

    // ------------------- Register Student Courses -------------------
    @PostMapping("/{studentId}/courses")
    public Map<String, Object> registerStudentCourses(
            @PathVariable String studentId,
            @RequestBody List<String> courseCodes
    ) {
        return registrationService.registerStudentCourses(studentId, courseCodes);
    }
}
