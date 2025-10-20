package com.stc.studentcourse.repository;

import com.stc.studentcourse.model.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, String> {
}
