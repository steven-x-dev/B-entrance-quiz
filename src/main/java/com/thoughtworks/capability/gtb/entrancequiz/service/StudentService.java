package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Student;
import com.thoughtworks.capability.gtb.entrancequiz.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    //TODO GTB-知识点: - StudentRepository 也可以注入进来
    public StudentService() {
        this.studentRepository = new StudentRepository();
    }

    public List<Student> list() {
        //TODO GTB-工程实践: - 排序的职责也可以放到 Repository 里
        List<Student> students = studentRepository.findAll();
        return students.stream().sorted(Comparator.comparing(Student::getId)).collect(Collectors.toList());
    }

}
