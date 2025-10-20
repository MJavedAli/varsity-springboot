package com.stc.studentcourse.controller;

import com.stc.studentcourse.model.entity.StudentCourse;
import com.stc.studentcourse.model.entity.StudentCourseKey;
import com.stc.studentcourse.service.StudentCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-courses")
public class StudentCourseController {

    private final StudentCourseService studentCourseService;

    public StudentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @GetMapping
    public List<StudentCourse> getAllStudentCourses() {
        return studentCourseService.getAllStudentCourses();
    }

    @GetMapping("/{studentId}/{courseCode}")
    public ResponseEntity<StudentCourse> getStudentCourse(@PathVariable String studentId,
                                                          @PathVariable String courseCode) {
        StudentCourseKey key = new StudentCourseKey();
        key.setStudentId(studentId);
        key.setCourseCode(courseCode);
        return studentCourseService.getStudentCourseById(key)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudentCourse createStudentCourse(@RequestBody StudentCourse studentCourse) {
        return studentCourseService.saveStudentCourse(studentCourse);
    }

    @PutMapping("/{studentId}/{courseCode}")
    public ResponseEntity<StudentCourse> updateStudentCourse(@PathVariable String studentId,
                                                             @PathVariable String courseCode,
                                                             @RequestBody StudentCourse studentCourse) {
        StudentCourseKey key = new StudentCourseKey();
        key.setStudentId(studentId);
        key.setCourseCode(courseCode);

        return studentCourseService.getStudentCourseById(key)
                .map(existing -> {
                    studentCourse.setId(key);
                    return ResponseEntity.ok(studentCourseService.saveStudentCourse(studentCourse));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{studentId}/{courseCode}")
    public ResponseEntity<Void> deleteStudentCourse(@PathVariable String studentId,
                                                    @PathVariable String courseCode) {
        StudentCourseKey key = new StudentCourseKey();
        key.setStudentId(studentId);
        key.setCourseCode(courseCode);
        studentCourseService.deleteStudentCourse(key);
        return ResponseEntity.noContent().build();
    }
}
