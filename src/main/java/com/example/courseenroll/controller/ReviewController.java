package com.example.courseenroll.controller;

import com.example.courseenroll.dto.ReviewDto;
import com.example.courseenroll.entity.Review;
import com.example.courseenroll.mapper.ReviewMapper;
import com.example.courseenroll.repository.ReviewRepository;
import com.example.courseenroll.service.base.BaseService;
import com.example.courseenroll.service.impl.DynamicServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final BaseService<ReviewDto, Long> dynamicService;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.dynamicService = new DynamicServiceImpl<Review, ReviewDto, Long>(reviewRepository, reviewMapper);
    }

    @PostMapping
    public ResponseEntity<ReviewDto> create(@RequestBody @Valid ReviewDto dto) {
        return ResponseEntity.ok(dynamicService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAll() {
        return ResponseEntity.ok(dynamicService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(dynamicService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> update(@PathVariable Long id, @RequestBody @Valid ReviewDto dto) {
        return ResponseEntity.ok(dynamicService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dynamicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
