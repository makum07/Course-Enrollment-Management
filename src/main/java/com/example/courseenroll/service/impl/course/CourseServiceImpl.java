package com.example.courseenroll.service.impl.course;

import com.example.courseenroll.dto.StudentDto;
import com.example.courseenroll.entity.Course;
import com.example.courseenroll.entity.Student;
import com.example.courseenroll.exception.ResourceNotFoundException;
import com.example.courseenroll.mapper.StudentMapper;
import com.example.courseenroll.repository.CourseRepository;
import com.example.courseenroll.repository.StudentRepository;
import com.example.courseenroll.service.custom.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             StudentRepository studentRepository,
                             StudentMapper studentMapper) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    @Transactional
    public String enrollStudentToCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));

        // Avoid duplicate enrollments
        if (!student.getEnrolledCourses().contains(course)) {
            student.getEnrolledCourses().add(course);
            course.getEnrolledStudents().add(student); // Optional: if bidirectional
            studentRepository.save(student);
        }

        return "Student with ID: " + studentId + " enrolled in course ID: " + courseId;
    }

    @Override
    public List<StudentDto> getStudentsForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

        return course.getEnrolledStudents()
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }
}
