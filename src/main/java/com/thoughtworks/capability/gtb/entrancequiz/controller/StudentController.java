package com.thoughtworks.capability.gtb.entrancequiz.controller;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Student;
import com.thoughtworks.capability.gtb.entrancequiz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/students")
    public ResponseEntity<List<Student>> list() {
        List<Student> students = studentService.list();
        return ResponseEntity.ok(students);
    }

    @PostMapping(path = "/student")
    public ResponseEntity<Student> create(@RequestBody @Validated Student student) {
        Student created = studentService.add(student);
        return ResponseEntity.created(null).body(created);
    }

}
