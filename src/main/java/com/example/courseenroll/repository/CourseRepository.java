package com.example.courseenroll.repository;

import com.example.courseenroll.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}
