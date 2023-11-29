package com.softgen.schooldms.controller;

import com.softgen.schooldms.model.dto.request.AddStudentsRequest;
import com.softgen.schooldms.model.dto.request.AssignTeacherRequest;
import com.softgen.schooldms.model.dto.request.CreateGroupRequest;
import com.softgen.schooldms.service.GroupService;
import jakarta.validation.constraints.NotEmpty;
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
    public ResponseEntity createGroup(@RequestBody CreateGroupRequest request) {
        return ResponseEntity.ok(groupService.createGroup(request));
    }

    @PostMapping
    public ResponseEntity modifyGroup(@RequestBody CreateGroupRequest request) {
        return ResponseEntity.ok(groupService.modifyGroup(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGroup(@PathVariable int id) {
        groupService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{groupNumber}/teacher")
    public ResponseEntity assignTeacher(@PathVariable int groupNumber, @RequestBody AssignTeacherRequest request) {
        groupService.assignTeacher(groupNumber, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{groupNumber}/students")
    public ResponseEntity addStudents(@PathVariable int groupNumber, @RequestBody AddStudentsRequest request) {
        groupService.addStudents(groupNumber, request);
        return ResponseEntity.ok().build();
    }

}
