package com.stc.studentcourse.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class StudentCourseKey implements Serializable {

    @Column(name = "student_id", length = 10)
    private String studentId;

    @Column(name = "course_code", length = 5)
    private String courseCode;

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    // hashCode and equals (required for @EmbeddedId)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentCourseKey)) return false;
        StudentCourseKey that = (StudentCourseKey) o;
        return studentId.equals(that.studentId) &&
                courseCode.equals(that.courseCode);
    }

    @Override
    public int hashCode() {
        return studentId.hashCode() + courseCode.hashCode();
    }
}
