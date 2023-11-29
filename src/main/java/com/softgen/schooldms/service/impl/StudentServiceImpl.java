package com.softgen.schooldms.service.impl;

import com.softgen.schooldms.exception.ApplicationException;
import com.softgen.schooldms.model.dto.PersonDto;
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
    public List<PersonDto> searchPerson(String firstname, String lastname, Long idNumber, LocalDateTime birthDate) {
        List<Student> studentList = studentRepository
                .findByFirstnameContainingAndLastnameContainingAndIdNumberAndBirthDate(firstname, lastname, idNumber, birthDate);

        return studentList.stream().map(p -> modelMapper.map(p, PersonDto.class)).collect(Collectors.toList());
    }

    @Override
    public PersonDto createPerson(PersonDto request) {
        Student student = modelMapper.map(request, Student.class);
        Student savedStudent = studentRepository.save(student);

        return modelMapper.map(savedStudent, PersonDto.class);
    }

    @Override
    public PersonDto modifyPerson(PersonDto request) {
        Student existingStudent = studentRepository.findById(request.getId())
                .orElseThrow(() -> new ApplicationException.EntryNotFoundException(role.getEntryName()));

        modelMapper.map(request, existingStudent);
        Student updatedStudent = studentRepository.save(existingStudent);

        return modelMapper.map(updatedStudent, PersonDto.class);
    }

    @Override
    public void deletePerson(int id) {
        studentRepository.findById(id).ifPresentOrElse(
                studentRepository::delete,
                () -> new ApplicationException.EntryNotFoundException(role.getEntryName())
        );
    }
}
