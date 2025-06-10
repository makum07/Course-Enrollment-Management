package com.example.courseenroll.mapper;

import com.example.courseenroll.dto.StudentDto;
import com.example.courseenroll.entity.Course;
import com.example.courseenroll.entity.Student;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StudentMapper implements Mapper<Student, StudentDto> {

    @Override
    public StudentDto toDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        if (student.getEnrolledCourses() != null) {
            dto.setEnrolledCourseIds(
                    student.getEnrolledCourses().stream()
                            .map(Course::getId)
                            .collect(Collectors.toSet())
            );
        }
        return dto;
    }

    @Override
    public Student toEntity(StudentDto dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        if (dto.getEnrolledCourseIds() != null) {
            Set<Course> courses = dto.getEnrolledCourseIds().stream()
                    .map(id -> {
                        Course course = new Course();
                        course.setId(id);
                        return course;
                    }).collect(Collectors.toSet());
            student.setEnrolledCourses(courses);
        }
        return student;
    }
}
