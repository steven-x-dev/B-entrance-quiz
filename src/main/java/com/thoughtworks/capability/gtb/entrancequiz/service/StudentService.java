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

    public StudentService() {
        this.studentRepository = new StudentRepository();
    }

    public List<Student> list() {
        List<Student> students = studentRepository.findAll();
        return students.stream().sorted(Comparator.comparing(Student::getId)).collect(Collectors.toList());
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

}
