package com.example.courseenroll.service.custom.course;

import com.example.courseenroll.dto.StudentDto;

import java.util.List;

public interface CourseService {
    String enrollStudentToCourse(Long courseId, Long studentId);
    List<StudentDto> getStudentsForCourse(Long courseId);
}
