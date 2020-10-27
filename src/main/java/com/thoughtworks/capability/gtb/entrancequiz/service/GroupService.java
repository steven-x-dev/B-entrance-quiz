package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Group;
import com.thoughtworks.capability.gtb.entrancequiz.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService() {
        this.groupRepository = new GroupRepository();
    }

    public List<Group> list() {
        List<Group> groups = groupRepository.findAll();
        return groups.stream().sorted(Comparator.comparing(Group::getId)).collect(Collectors.toList());
    }

}
