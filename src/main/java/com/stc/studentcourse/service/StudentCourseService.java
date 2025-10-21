package com.stc.studentcourse.service;

import com.stc.studentcourse.model.entity.StudentCourse;
import com.stc.studentcourse.model.entity.StudentCourseKey;
import com.stc.studentcourse.repository.StudentCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;

    public StudentCourseService(StudentCourseRepository studentCourseRepository) {
        this.studentCourseRepository = studentCourseRepository;
    }
    public List<StudentCourse> getCoursesByStudentId(String studentId) {
    return studentCourseRepository.findByIdStudentId(studentId);
}


    public List<StudentCourse> getAllStudentCourses() {
        return studentCourseRepository.findAll();
    }

    public Optional<StudentCourse> getStudentCourseById(StudentCourseKey id) {
        return studentCourseRepository.findById(id);
    }

    public StudentCourse saveStudentCourse(StudentCourse studentCourse) {
        return studentCourseRepository.save(studentCourse);
    }

    public void deleteStudentCourse(StudentCourseKey id) {
        studentCourseRepository.deleteById(id);
    }

    
}
