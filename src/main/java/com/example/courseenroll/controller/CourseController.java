package com.example.courseenroll.controller;

import com.example.courseenroll.dto.CourseDto;
import com.example.courseenroll.mapper.CourseMapper;
import com.example.courseenroll.repository.CourseRepository;
import com.example.courseenroll.service.base.BaseService;
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

    @Autowired
    public CourseController(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.dynamicService = new DynamicServiceImpl<>(courseRepository, courseMapper);
    }

    @PostMapping
    public ResponseEntity<CourseDto> create(@RequestBody @Valid CourseDto dto) {
        return ResponseEntity.ok(dynamicService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAll() {
        return ResponseEntity.ok(dynamicService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(dynamicService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> update(@PathVariable Long id, @RequestBody @Valid CourseDto dto) {
        return ResponseEntity.ok(dynamicService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dynamicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
