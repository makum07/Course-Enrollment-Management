package com.example.courseenroll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String comment;
    private Integer rating;

    private Long studentId;
    private Long courseId;

//    private List<Long> courseIds;
}
