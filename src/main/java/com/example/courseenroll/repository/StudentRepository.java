package com.example.courseenroll.repository;

import com.example.courseenroll.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}

