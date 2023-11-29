package com.softgen.schooldms.controller;

import com.softgen.schooldms.model.dto.AddStudentsDto;
import com.softgen.schooldms.model.dto.AssignTeacherDto;
import com.softgen.schooldms.model.dto.GroupDto;
import com.softgen.schooldms.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/group")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{groupNumber}")
    public ResponseEntity searchGroups(@PathVariable int groupNumber) {
        return ResponseEntity.ok(groupService.searchGroup(groupNumber));

    }

    @PutMapping
    public ResponseEntity createGroup(@RequestBody GroupDto request) {
        return ResponseEntity.ok(groupService.createGroup(request));
    }

    @PostMapping
    public ResponseEntity modifyGroup(@RequestBody GroupDto request) {
        return ResponseEntity.ok(groupService.modifyGroup(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGroup(@PathVariable int id) {
        groupService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{groupId}/teacher")
    public ResponseEntity assignTeacher(@PathVariable int groupId, @RequestBody AssignTeacherDto request) {
        groupService.assignTeacher(groupId, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{groupId}/students")
    public ResponseEntity addStudents(@PathVariable int groupId, @RequestBody AddStudentsDto request) {
        groupService.addStudents(groupId, request);
        return ResponseEntity.ok().build();
    }

}
