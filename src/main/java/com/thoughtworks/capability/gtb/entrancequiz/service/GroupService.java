package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Group;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Student;
import com.thoughtworks.capability.gtb.entrancequiz.repository.GroupRepository;
import com.thoughtworks.capability.gtb.entrancequiz.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    private final StudentRepository studentRepository;

    public GroupService() {
        this.groupRepository = new GroupRepository();
        this.studentRepository = new StudentRepository();
    }

    public List<Group> list() {
        List<Group> groups = groupRepository.findAll();
        return groups.stream().sorted(Comparator.comparing(Group::getId)).collect(Collectors.toList());
    }

    public List<Group> updateGroups() {
        List<Group> groups = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            Group group = new Group();
            group.setId(i + 1);
            groups.add(group);
        }
        List<Student> students = studentRepository.findAll();
        while (!students.isEmpty()) {
            for (Group group : groups) {
                if (students.isEmpty()) break;
                int index = (int) (Math.random() * students.size());
                Student student = students.get(index);
                group.addStudent(student);
                students.remove(student);
            }
        }
        return groupRepository.save(groups);
    }

}
