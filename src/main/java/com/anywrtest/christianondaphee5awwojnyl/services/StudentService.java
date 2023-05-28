package com.anywrtest.christianondaphee5awwojnyl.services;

import com.anywrtest.christianondaphee5awwojnyl.entities.Students;
import com.anywrtest.christianondaphee5awwojnyl.repositories.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentsRepository repository;

    public Page<Students> getStudentsByClassAndTeacher(String className, String teacherFullName, Pageable pageRequest) {
        return repository.findByClassNameAndTeacherFullName(className, teacherFullName, pageRequest);
    }
}
