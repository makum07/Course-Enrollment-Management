package com.example.courseenroll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Long id;
    private String title;
    private String description;

    private Long categoryId;
    private Set<Long> enrolledStudentIds;
    private List<Long> reviewIds;
}

