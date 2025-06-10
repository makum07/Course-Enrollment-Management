package com.example.courseenroll.controller;

import com.example.courseenroll.dto.CourseDto;
import com.example.courseenroll.dto.StudentDto;
import com.example.courseenroll.entity.Student;
import com.example.courseenroll.mapper.StudentMapper;
import com.example.courseenroll.repository.StudentRepository;
import com.example.courseenroll.service.base.BaseService;
import com.example.courseenroll.service.impl.DynamicServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.courseenroll.service.custom.student.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final BaseService<StudentDto, Long> dynamicService;

    // For future use:
     private final StudentService studentService;

    @Autowired
    public StudentController(StudentRepository studentRepository, StudentMapper studentMapper , StudentService studentService ) {
        this.dynamicService = new DynamicServiceImpl<Student, StudentDto, Long>(studentRepository, studentMapper);
         this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<StudentDto> create(@RequestBody @Valid StudentDto dto) {
        return ResponseEntity.ok(dynamicService.create(dto));
        // return ResponseEntity.ok(studentService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAll() {
        return ResponseEntity.ok(dynamicService.getAll());
        // return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(dynamicService.getById(id));
        // return ResponseEntity.ok(studentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update(@PathVariable Long id, @RequestBody @Valid StudentDto dto) {
        return ResponseEntity.ok(dynamicService.update(id, dto));
        // return ResponseEntity.ok(studentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dynamicService.delete(id);
        // studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Get courses for student
    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<CourseDto>> getCoursesForStudent(@PathVariable Long studentId) {
        List<CourseDto> dtos = studentService.getCoursesForStudent(studentId);
        return ResponseEntity.ok(dtos);
    }
}
