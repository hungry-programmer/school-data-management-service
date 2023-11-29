package com.softgen.schooldms.controller;


import com.softgen.schooldms.model.dto.PersonDto;
import com.softgen.schooldms.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/student")
public class StudentController {

    private final PersonService personService;

    @Autowired
    public StudentController(@Qualifier("student") PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/search")
    public ResponseEntity searchStudents(@RequestParam(required = false) String firstname,
                                         @RequestParam(required = false) String lastname,
                                         @RequestParam(required = false) Long idNumber,
                                         @RequestParam(required = false) LocalDateTime birthDate) {

        return ResponseEntity.ok(personService.searchPerson(firstname, lastname, idNumber, birthDate));
    }

    @PutMapping
    public ResponseEntity createStudent(@Valid @RequestBody PersonDto request) {

        return ResponseEntity.ok(personService.createPerson(request));
    }

    @PostMapping
    public ResponseEntity modifyStudent(@Valid @RequestBody PersonDto request) {

        return ResponseEntity.ok(personService.modifyPerson(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id) {
        personService.deletePerson(id);

        return ResponseEntity.ok().build();
    }

}
