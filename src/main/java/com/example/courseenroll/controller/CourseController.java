package com.example.courseenroll.controller;

import com.example.courseenroll.dto.CourseDto;
import com.example.courseenroll.dto.StudentDto;
import com.example.courseenroll.mapper.CourseMapper;
import com.example.courseenroll.repository.CourseRepository;
import com.example.courseenroll.service.base.BaseService;
import com.example.courseenroll.service.custom.course.CourseService;// dependent on abstractions, not implementations.
//Spring works with interfaces, and this pattern follows the Dependency Inversion Principle from SOLID principles:
//
//Your CourseController depends on the interface, not the implementation.
//
//Spring automatically injects the appropriate @Service-annotated class that implements that interface (CourseServiceImpl).
//Spring sees:
//
//        "Ah! CourseService is an interface."
//
//        "Do I have a bean that implements it?"
//
//        "Yes! Here's CourseServiceImpl — I’ll inject that."
//
//No need to manually write new CourseServiceImpl() or inject it directly

import com.example.courseenroll.service.impl.DynamicServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final BaseService<CourseDto, Long> dynamicService;
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseRepository courseRepository, CourseMapper courseMapper, CourseService courseService) {
        this.dynamicService = new DynamicServiceImpl<>(courseRepository, courseMapper);
        this.courseService = courseService;
    }

    // ✅ Create course
    @PostMapping
    public ResponseEntity<CourseDto> create(@RequestBody @Valid CourseDto dto) {
        return ResponseEntity.ok(dynamicService.create(dto));
    }

    // ✅ Get all courses
    @GetMapping
    public ResponseEntity<List<CourseDto>> getAll() {
        return ResponseEntity.ok(dynamicService.getAll());
    }

    // ✅ Get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(dynamicService.getById(id));
    }

    // ✅ Update course
    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> update(@PathVariable Long id, @RequestBody @Valid CourseDto dto) {
        return ResponseEntity.ok(dynamicService.update(id, dto));
    }

    // ✅ Delete course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dynamicService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Enroll student to course
    @PostMapping("/{courseId}/enroll/{studentId}")
    public ResponseEntity<String> enrollStudentToCourse(
            @PathVariable Long courseId,
            @PathVariable Long studentId
    ) {
        String message = courseService.enrollStudentToCourse(courseId, studentId);
        return ResponseEntity.ok(message);
    }

    // 🔹 Get students enrolled in course
    @GetMapping("/{courseId}/students")
    public ResponseEntity<List<StudentDto>> getStudentsForCourse(@PathVariable Long courseId) {
        List<StudentDto> dtos = courseService.getStudentsForCourse(courseId);
        return ResponseEntity.ok(dtos);
    }

}
