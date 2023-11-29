package com.softgen.schooldms.controller;

import com.softgen.schooldms.model.dto.GroupDto;
import com.softgen.schooldms.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/group")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{groupNumber}")
    public ResponseEntity searchGroups(@PathVariable int groupNumber) {
        try {
            return ResponseEntity.ok(groupService.searchGroup(groupNumber));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity createGroup(@RequestBody GroupDto request) {
        try {
            return ResponseEntity.ok(groupService.createGroup(request));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity modifyGroup(@RequestBody GroupDto request) {
        try {
            return ResponseEntity.ok(groupService.modifyGroup(request));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGroup(@PathVariable int id) {
        try {
            groupService.deleteGroup(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
