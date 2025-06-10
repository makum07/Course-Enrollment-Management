package com.example.courseenroll.mapper;

import com.example.courseenroll.dto.StudentDto;
import com.example.courseenroll.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper implements Mapper<Student, StudentDto> {

    @Override
    public StudentDto toDto(Student entity) {
        return new StudentDto(entity.getId(), entity.getName(), entity.getEmail());
    }

    @Override
    public Student toEntity(StudentDto dto) {
        Student s = new Student();
        s.setId(dto.getId());
        s.setName(dto.getName());
        s.setEmail(dto.getEmail());
        return s;
    }
}
