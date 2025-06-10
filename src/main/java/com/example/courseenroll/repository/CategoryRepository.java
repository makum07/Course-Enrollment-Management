package com.example.courseenroll.repository;

import com.example.courseenroll.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}