package com.softgen.schooldms.controller;

import com.softgen.schooldms.model.dto.request.CreatePersonRequest;
import com.softgen.schooldms.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/teacher")
public class TeacherController {

    private final PersonService personService;

    @Autowired
    public TeacherController(@Qualifier("teacher") PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/search")
    public ResponseEntity searchTeachers(@RequestParam(required = false) String firstname,
                                         @RequestParam(required = false) String lastname,
                                         @RequestParam(required = false) Long idNumber,
                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                         @RequestParam(required = false) LocalDateTime birthDate) {

        return ResponseEntity.ok(personService.searchPerson(firstname, lastname, idNumber, birthDate));
    }

    @PutMapping
    public ResponseEntity createTeacher(@RequestBody @Valid CreatePersonRequest request) {

        return ResponseEntity.ok(personService.createPerson(request));
    }

    @PostMapping
    public ResponseEntity modifyTeacher(@RequestBody @Valid CreatePersonRequest request) {

        return ResponseEntity.ok(personService.modifyPerson(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeacher(@PathVariable int id) {
        personService.deletePerson(id);

        return ResponseEntity.ok().build();
    }

}

