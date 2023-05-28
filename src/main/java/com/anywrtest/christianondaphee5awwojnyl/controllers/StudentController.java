package com.anywrtest.christianondaphee5awwojnyl.controllers;

import com.anywrtest.christianondaphee5awwojnyl.entities.Students;
import com.anywrtest.christianondaphee5awwojnyl.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService service;

    @GetMapping("/get-by-class-and-teacher-name")
    public Page<Students> getStudentsByClassAndTeacherName(@RequestParam(defaultValue = "%")String className,
                                                           @RequestParam(defaultValue = "%")String teacherFullName,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size){
        final Page<Students> content = service.getStudentsByClassAndTeacher(className,teacherFullName, PageRequest.of(page, size));
        return content;
    }
}
