package com.example.courseenroll.mapper;

import com.example.courseenroll.dto.CategoryDto;
import com.example.courseenroll.entity.Category;
import com.example.courseenroll.entity.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper implements Mapper<Category, CategoryDto> {

    @Override
    public CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());

        if (category.getCourses() != null) {
            dto.setCourseIds(
                    category.getCourses().stream()
                            .map(Course::getId)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    @Override
    public Category toEntity(CategoryDto dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());

        if (dto.getCourseIds() != null) {
            List<Course> courses = dto.getCourseIds().stream()
                    .map(id -> {
                        Course course = new Course();
                        course.setId(id);
                        return course;
                    }).collect(Collectors.toList());
            category.setCourses(courses);
        }

        return category;
    }
}
