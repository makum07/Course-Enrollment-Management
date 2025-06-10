package com.example.courseenroll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String name;
    private String email;

    private Set<Long> enrolledCourseIds;
}

