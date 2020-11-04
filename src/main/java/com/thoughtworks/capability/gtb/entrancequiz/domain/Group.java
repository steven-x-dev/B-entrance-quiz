package com.thoughtworks.capability.gtb.entrancequiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    //TODO GTB-完成度: - Group 没有 name 字段
    private Integer id;

    private List<Student> students;

    public void addStudent(Student student) {
        //TODO GTB-工程实践: - 初始化时就可以 new 了，不用等到用的时候
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

}
