package com.example.courseenroll.mapper;

import com.example.courseenroll.dto.CourseDto;
import com.example.courseenroll.entity.Category;
import com.example.courseenroll.entity.Course;
import com.example.courseenroll.entity.Review;
import com.example.courseenroll.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CourseMapper implements Mapper<Course, CourseDto> {

    @Override
    public CourseDto toDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());

        if (course.getCategory() != null) {
            dto.setCategoryId(course.getCategory().getId());
        }

        if (course.getEnrolledStudents() != null) {
            dto.setEnrolledStudentIds(
                    course.getEnrolledStudents().stream()
                            .map(Student::getId)
                            .collect(Collectors.toSet())
            );
        }

        if (course.getReviews() != null) {
            dto.setReviewIds(
                    course.getReviews().stream()
                            .map(Review::getId)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    @Override
    public Course toEntity(CourseDto dto) {
        Course course = new Course();
        course.setId(dto.getId());
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());

        if (dto.getCategoryId() != null) {
            Category category = new Category();
            category.setId(dto.getCategoryId());
            course.setCategory(category);
        }

        if (dto.getEnrolledStudentIds() != null) {
            Set<Student> students = dto.getEnrolledStudentIds().stream()
                    .map(id -> {
                        Student student = new Student();
                        student.setId(id);
                        return student;
                    }).collect(Collectors.toSet());
            course.setEnrolledStudents(students);
        }

        if (dto.getReviewIds() != null) {
            List<Review> reviews = dto.getReviewIds().stream()
                    .map(id -> {
                        Review review = new Review();
                        review.setId(id);
                        return review;
                    }).collect(Collectors.toList());
            course.setReviews(reviews);
        }

        return course;
    }
}
