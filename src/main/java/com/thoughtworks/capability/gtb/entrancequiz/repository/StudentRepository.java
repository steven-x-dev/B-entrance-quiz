package com.thoughtworks.capability.gtb.entrancequiz.repository;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

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

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    public Student save(Student student) {
        int id = students.size() + 1;
        String name = student.getName();
        Student saved = new Student(id, name);
        students.add(saved);
        return saved;
    }

}
