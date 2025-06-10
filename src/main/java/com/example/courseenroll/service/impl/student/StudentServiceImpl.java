package com.example.courseenroll.service.impl.student;

import com.example.courseenroll.dto.CourseDto;
import com.example.courseenroll.entity.Student;
import com.example.courseenroll.exception.ResourceNotFoundException;
import com.example.courseenroll.mapper.CourseMapper;
import com.example.courseenroll.repository.StudentRepository;
import com.example.courseenroll.service.custom.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseMapper courseMapper) {
        this.studentRepository = studentRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<CourseDto> getCoursesForStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));

        return student.getEnrolledCourses()
                .stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }
}

