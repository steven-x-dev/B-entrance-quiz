package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static final List<Student> students = new ArrayList<Student>() {{
        add(new Student(1 , "成吉思汗"));
        add(new Student(2 , "鲁班七号"));
        add(new Student(3 , "太乙真人"));
        add(new Student(4 , "钟无艳"));
        add(new Student(5 , "花木兰"));
        add(new Student(6 , "雅典娜"));
        add(new Student(7 , "芈月"));
        add(new Student(8 , "白起"));
        add(new Student(9 , "刘禅"));
        add(new Student(10, "庄周"));
        add(new Student(11, "马超"));
        add(new Student(12, "刘备"));
        add(new Student(13, "哪吒"));
        add(new Student(14, "大乔"));
        add(new Student(15, "蔡文姬"));
    }};

    public List<Student> list() {
        return students.stream().sorted(Comparator.comparing(Student::getId)).collect(Collectors.toList());
    }

}
