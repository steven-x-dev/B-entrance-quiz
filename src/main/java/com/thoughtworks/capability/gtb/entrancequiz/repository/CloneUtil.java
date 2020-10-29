package com.thoughtworks.capability.gtb.entrancequiz.repository;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Group;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CloneUtil {

    public static List<Group> cloneGroups(List<Group> src) {
        return clone(src, g -> new Group(g.getId(), cloneStudents(g.getStudents())));
    }

    public static List<Student> cloneStudents(List<Student> src) {
        return clone(src, s -> new Student(s.getId(), s.getName()));
    }

    private static <T> List<T> clone(List<T> src, Function<T, T> transformer) {
        if (src == null) {
            return null;
        }
        if (src.size() == 0) {
            return new ArrayList<>(0);
        }
        List<T> cloned = new ArrayList<>(src.size());
        src.forEach(t -> cloned.add(transformer.apply(t)));
        return cloned;
    }

    private CloneUtil() {}
}
