package com.example.courseenroll.mapper;

import com.example.courseenroll.dto.CourseDto;
import com.example.courseenroll.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper implements Mapper<Course, CourseDto> {

    @Override
    public CourseDto toDto(Course course) {
        return new CourseDto(course.getId(), course.getTitle(), course.getDescription());
    }

    @Override
    public Course toEntity(CourseDto dto) {
        Course course = new Course();
        course.setId(dto.getId());
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        return course;
    }
}
