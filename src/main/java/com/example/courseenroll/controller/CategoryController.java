package com.example.courseenroll.controller;

import com.example.courseenroll.dto.CategoryDto;
import com.example.courseenroll.mapper.CategoryMapper;
import com.example.courseenroll.repository.CategoryRepository;
import com.example.courseenroll.service.base.BaseService;
import com.example.courseenroll.service.impl.DynamicServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final BaseService<CategoryDto, Long> dynamicService;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.dynamicService = new DynamicServiceImpl<>(categoryRepository, categoryMapper);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody @Valid CategoryDto dto) {
        return ResponseEntity.ok(dynamicService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(dynamicService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(dynamicService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody @Valid CategoryDto dto) {
        return ResponseEntity.ok(dynamicService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dynamicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

