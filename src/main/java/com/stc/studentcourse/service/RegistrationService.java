package com.stc.studentcourse.service;

import com.stc.studentcourse.model.entity.*;
import com.stc.studentcourse.util.Utils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private final StudentService studentService;
    private final ProfessorService professorService;
    private final CourseService courseService;
    private final StudentCourseService studentCourseService;

    public RegistrationService(StudentService studentService,
                               ProfessorService professorService,
                               CourseService courseService,
                               StudentCourseService studentCourseService) {
        this.studentService = studentService;
        this.professorService = professorService;
        this.courseService = courseService;
        this.studentCourseService = studentCourseService;
    }

    // ------------------- Register Student -------------------
    public Map<String, Object> registerStudent(Student student) {
        Map<String, Object> response = new HashMap<>();

        if (!Utils.isValidMobile(student.getMobileNumber())) {
            response.put("status", "Failed");
            response.put("message", "Invalid mobile number format");
            return response;
        }

        student.setUniversityId(Utils.generateUniversityId());
        Student saved = studentService.saveStudent(student);
        response.put("status", "Success");
        response.put("universityId", saved.getUniversityId());
        return response;
    }

    // ------------------- Register Professor -------------------
    public Map<String, Object> registerProfessor(Professor professor) {
        Map<String, Object> response = new HashMap<>();

        if (!Utils.isValidMobile(professor.getMobileNumber())) {
            response.put("status", "Failed");
            response.put("message", "Invalid mobile number format");
            return response;
        }

        if (!Utils.isValidTitle(professor.getTitle())) {
            response.put("status", "Failed");
            response.put("message", "Invalid title. Allowed: Associate professor, Assistant professor, Lecturer");
            return response;
        }

        professor.setUniversityId(Utils.generateUniversityId());
        Professor saved = professorService.saveProfessor(professor);
        response.put("status", "Success");
        response.put("universityId", saved.getUniversityId());
        return response;
    }

    // ------------------- Register Course -------------------
    public Map<String, Object> registerCourse(Course course) {
        Map<String, Object> response = new HashMap<>();

        if (!Utils.isValidCourseCode(course.getCourseCode())) {
            response.put("status", "Failed");
            response.put("message", "Invalid course code format (e.g., IS230)");
            return response;
        }

        courseService.saveCourse(course);
        response.put("status", "Success");
        return response;
    }

    // ------------------- Register Student Courses -------------------
    public Map<String, Object> registerStudentCourses(String studentId, List<String> courseCodes) {
        Map<String, Object> response = new HashMap<>();
        Optional<Student> studentOpt = studentService.getStudentById(studentId);
        if (studentOpt.isEmpty()) {
            response.put("status", "Failed");
            response.put("message", "Student not found");
            return response;
        }

        // Check existing courses
        List<StudentCourse> existing = studentCourseService.getAllStudentCourses()
                .stream()
                .filter(sc -> sc.getStudent().getUniversityId().equals(studentId))
                .collect(Collectors.toList());

        if (existing.size() + courseCodes.size() > 6) {
            response.put("status", "Failed");
            response.put("message", "Allowed courses number exceeded");
            return response;
        }

        for (String code : courseCodes) {
            Optional<Course> courseOpt = courseService.getCourseById(code);
            if (courseOpt.isEmpty()) continue; // Skip invalid courses

            StudentCourseKey key = new StudentCourseKey();
            key.setStudentId(studentId);
            key.setCourseCode(code);

            StudentCourse sc = new StudentCourse();
            sc.setId(key);
            sc.setStudent(studentOpt.get());
            sc.setCourse(courseOpt.get());
            studentCourseService.saveStudentCourse(sc);
        }

        response.put("status", "Success");
        return response;
    }

    // ------------------- Inquiry Methods -------------------

    public Optional<Student> getStudentProfileById(String universityId, String nationalId, String fullName) {
        List<Student> students = studentService.getAllStudents();

        return students.stream().filter(s -> 
                (universityId != null && s.getUniversityId().equals(universityId)) ||
                (nationalId != null && s.getNationalId().equals(nationalId)) ||
                (fullName != null && (s.getFirstName() + " " + s.getLastName()).equalsIgnoreCase(fullName))
        ).findFirst();
    }

    public List<Student> getStudentProfileDateRange(LocalDate from, LocalDate to) {
        return studentService.getAllStudents().stream()
                .filter(s -> !s.getRegistrationDate().isBefore(from) && !s.getRegistrationDate().isAfter(to))
                .collect(Collectors.toList());
    }

    public Optional<Professor> getProfessorProfileById(String universityId, String nationalId, String fullName) {
        List<Professor> professors = professorService.getAllProfessors();

        return professors.stream().filter(p ->
                (universityId != null && p.getUniversityId().equals(universityId)) ||
                (nationalId != null && p.getNationalId().equals(nationalId)) ||
                (fullName != null && (p.getFirstName() + " " + p.getLastName()).equalsIgnoreCase(fullName))
        ).findFirst();
    }

    // public Map<String, Object> getStudentCourseDetails(String studentId) {
    //     Map<String, Object> response = new HashMap<>();
    //     Optional<Student> studentOpt = studentService.getStudentById(studentId);
    //     if (studentOpt.isEmpty()) {
    //         response.put("status", "Failed");
    //         response.put("message", "No record found");
    //         return response;
    //     }

    //     List<StudentCourse> studentCourses = studentCourseService.getAllStudentCourses()
    //             .stream()
    //             .filter(sc -> sc.getStudent().getUniversityId().equals(studentId))
    //             .collect(Collectors.toList());

    //     response.put("studentProfile", Map.of(
    //             "universityId", studentOpt.get().getUniversityId(),
    //             "firstName", studentOpt.get().getFirstName(),
    //             "lastName", studentOpt.get().getLastName()
    //     ));

    //     List<Map<String, String>> courses = studentCourses.stream().map(sc -> Map.of(
    //             "courseCode", sc.getCourse().getCourseCode(),
    //             "courseName", sc.getCourse().getCourseName(),
    //             "professorFirstName", sc.getCourse().getProfessor().getFirstName(),
    //             "professorLastName", sc.getCourse().getProfessor().getLastName()
    //     )).collect(Collectors.toList());

    //     response.put("courses", courses);
    //     return response;
    // }

    public Map<String, Object> getStudentCourseDetails(String studentId) {
    Map<String, Object> response = new HashMap<>();

    Optional<Student> studentOpt = studentService.getStudentById(studentId);
    if (studentOpt.isEmpty()) {
        response.put("status", "Failed");
        response.put("message", "No record found");
        return response;
    }

    List<StudentCourse> studentCourses = studentCourseService.getCoursesByStudentId(studentId);

    if (studentCourses.isEmpty()) {
        response.put("status", "Failed");
        response.put("message", "No record found");
        return response;
    }

    response.put("studentProfile", Map.of(
            "universityId", studentOpt.get().getUniversityId(),
            "firstName", studentOpt.get().getFirstName(),
            "lastName", studentOpt.get().getLastName()
    ));

    List<Map<String, String>> courses = studentCourses.stream().map(sc -> Map.of(
            "courseCode", sc.getCourse().getCourseCode(),
            "courseName", sc.getCourse().getCourseName()
            // "professorFirstName", sc.getCourse().getProfessor().getFirstName(),
            // "professorLastName", sc.getCourse().getProfessor().getLastName()
    )).collect(Collectors.toList());

    response.put("courses", courses);
    return response;
}

    public List<Course> getAllCourses() {
    return courseService.getAllCourses();
}

}

