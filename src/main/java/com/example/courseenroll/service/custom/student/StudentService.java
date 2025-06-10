package com.example.courseenroll.service.custom.student;

import com.example.courseenroll.dto.CourseDto;

import java.util.List;

public interface StudentService {
    List<CourseDto> getCoursesForStudent(Long studentId);
}
