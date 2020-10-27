package com.thoughtworks.capability.gtb.entrancequiz.controller;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Group;
import com.thoughtworks.capability.gtb.entrancequiz.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path = "/groups")
    public ResponseEntity<List<Group>> list() {
        List<Group> groups = groupService.list();
        return ResponseEntity.ok(groups);
    }

    @PatchMapping(path = "/groups")
    public ResponseEntity<List<Group>> updateGroups() {
        List<Group> groups = groupService.updateGroups();
        return ResponseEntity.ok(groups);
    }

}
