package com.thoughtworks.capability.gtb.entrancequiz.repository;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository {

    private static final List<Group> groups = new ArrayList<>(6);

    public List<Group> save(List<Group> groups) {
        GroupRepository.groups.clear();
        GroupRepository.groups.addAll(CloneUtil.cloneGroups(groups));
        return CloneUtil.cloneGroups(GroupRepository.groups);
    }

    public List<Group> findAll() {
        return CloneUtil.cloneGroups(groups);
    }

}
