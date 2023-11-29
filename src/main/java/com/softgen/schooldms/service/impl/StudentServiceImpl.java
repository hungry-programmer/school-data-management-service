package com.softgen.schooldms.service.impl;

import com.softgen.schooldms.exception.ApplicationException;
import com.softgen.schooldms.model.dto.request.CreatePersonRequest;
import com.softgen.schooldms.model.dto.response.PersonResponse;
import com.softgen.schooldms.model.dto.response.StudentResponse;
import com.softgen.schooldms.model.entity.Student;
import com.softgen.schooldms.model.enums.Role;
import com.softgen.schooldms.repository.StudentRepository;
import com.softgen.schooldms.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Qualifier("student")
@RequiredArgsConstructor
public class StudentServiceImpl implements PersonService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final Role role = Role.STUDENT;

    @Override
    public List<PersonResponse> searchPerson(String firstname, String lastname, Long idNumber, LocalDateTime birthDate) {
        List<Student> studentList = studentRepository
                .findAllByFirstnameContainingOrLastnameContainingOrIdNumberOrBirthDate(firstname, lastname, idNumber, birthDate);

        return studentList.stream().map(p -> modelMapper.map(p, StudentResponse.class)).collect(Collectors.toList());
    }

    @Override
    public PersonResponse createPerson(CreatePersonRequest request) {
        Student student = modelMapper.map(request, Student.class);
        Student savedStudent = studentRepository.save(student);

        return modelMapper.map(savedStudent, StudentResponse.class);
    }

    @Override
    public PersonResponse modifyPerson(CreatePersonRequest request) {
        Student existingStudent = studentRepository.findById(request.getId())
                .orElseThrow(() -> new ApplicationException.EntryNotFoundException(role.getEntryName()));

        modelMapper.map(request, existingStudent);
        Student updatedStudent = studentRepository.save(existingStudent);

        return modelMapper.map(updatedStudent, StudentResponse.class);
    }

    @Override
    public void deletePerson(int id) {
        studentRepository.findById(id).ifPresentOrElse(
                studentRepository::delete,
                () -> {
                    throw new ApplicationException.EntryNotFoundException(role.getEntryName());
                }
        );
    }

}
