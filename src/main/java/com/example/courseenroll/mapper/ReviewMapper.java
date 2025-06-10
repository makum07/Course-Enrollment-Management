package com.example.courseenroll.mapper;

import com.example.courseenroll.dto.ReviewDto;
import com.example.courseenroll.entity.Course;
import com.example.courseenroll.entity.Review;
import com.example.courseenroll.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper implements Mapper<Review, ReviewDto> {

    @Override
    public ReviewDto toDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setComment(review.getComment());
        dto.setRating(review.getRating());
        if (review.getStudent() != null) {
            dto.setStudentId(review.getStudent().getId());
        }
        if (review.getCourse() != null) {
            dto.setCourseId(review.getCourse().getId());
        }
        return dto;
    }

    @Override
    public Review toEntity(ReviewDto dto) {
        Review review = new Review();
        review.setId(dto.getId());
        review.setComment(dto.getComment());
        review.setRating(dto.getRating());

        if (dto.getStudentId() != null) {
            Student student = new Student();
            student.setId(dto.getStudentId());
            review.setStudent(student);
        }

        if (dto.getCourseId() != null) {
            Course course = new Course();
            course.setId(dto.getCourseId());
            review.setCourse(course);
        }

        return review;
    }
}
