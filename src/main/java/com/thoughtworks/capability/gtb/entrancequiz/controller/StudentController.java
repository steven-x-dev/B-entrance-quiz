package com.thoughtworks.capability.gtb.entrancequiz.controller;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Student;
import com.thoughtworks.capability.gtb.entrancequiz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//TODO GTB-完成度: - 没有添加学员的 API
//TODO GTB-工程实践: + 小步提交
@RestController
public class StudentController {

    private final StudentService studentService;

    //TODO GTB-知识点: - 这里不需要 @Autowired 了
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/students")
    //TODO GTB-知识点: - 可以用 @ResponseStatus 简化代码
    public ResponseEntity<List<Student>> list() {
        List<Student> students = studentService.list();
        return ResponseEntity.ok(students);
    }

}
