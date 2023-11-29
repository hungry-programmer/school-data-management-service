package com.softgen.schooldms.service;

import com.softgen.schooldms.model.dto.request.CreatePersonRequest;
import com.softgen.schooldms.model.dto.response.PersonResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonService {

    List<PersonResponse> searchPerson(String firstname, String lastname, Long idNumber, LocalDateTime birthDate);

    PersonResponse createPerson(CreatePersonRequest request);

    PersonResponse modifyPerson(CreatePersonRequest request);

    void deletePerson(int id);

}
