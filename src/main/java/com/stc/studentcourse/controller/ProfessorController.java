package com.stc.studentcourse.controller;

import com.stc.studentcourse.model.entity.Professor;
import com.stc.studentcourse.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    private final RegistrationService registrationService;

    public ProfessorController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // ------------------- Register Professor -------------------
    @PostMapping("/register")
    public Map<String, Object> registerProfessor(@RequestBody Professor professor) {
        return registrationService.registerProfessor(professor);
    }

    // ------------------- Get Professor by ID / National ID / Full Name -------------------
    @GetMapping("/profile")
    public Optional<Professor> getProfessorProfile(
            @RequestParam(required = false) String universityId,
            @RequestParam(required = false) String nationalId,
            @RequestParam(required = false) String fullName
    ) {
        return registrationService.getProfessorProfileById(universityId, nationalId, fullName);
    }
}
