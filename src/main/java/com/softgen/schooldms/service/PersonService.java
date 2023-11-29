package com.softgen.schooldms.service;

import com.softgen.schooldms.model.dto.PersonDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonService {
    List<PersonDto> searchPerson(String firstname, String lastname, Long idNumber, LocalDateTime birthDate);
    PersonDto createPerson(PersonDto request);

    PersonDto modifyPerson(PersonDto request);

    void deletePerson(int id);

}
