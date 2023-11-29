package com.softgen.schooldms.service.impl;

import com.softgen.schooldms.exception.ApplicationException;
import com.softgen.schooldms.model.dto.PersonDto;
import com.softgen.schooldms.model.entity.Teacher;
import com.softgen.schooldms.model.enums.Role;
import com.softgen.schooldms.repository.TeacherRepository;
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
@Qualifier("teacher")
@RequiredArgsConstructor
public class TeacherServiceImpl implements PersonService {

    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;
    private final Role role = Role.TEACHER;

    @Override
    public List<PersonDto> searchPerson(String firstname, String lastname, Long idNumber, LocalDateTime birthDate) {
        List<Teacher> teacherList = teacherRepository
                .findByFirstnameContainingAndLastnameContainingAndIdNumberAndBirthDate(firstname, lastname, idNumber, birthDate);

        return teacherList.stream().map(p -> modelMapper.map(p, PersonDto.class)).collect(Collectors.toList());
    }

    @Override
    public PersonDto createPerson(PersonDto request) {
        Teacher teacher = modelMapper.map(request, Teacher.class);
        Teacher savedTeacher = teacherRepository.save(teacher);

        return modelMapper.map(savedTeacher, PersonDto.class);
    }

    @Override
    public PersonDto modifyPerson(PersonDto request) {
        Teacher existingTeacher = teacherRepository.findById(request.getId())
                .orElseThrow(() -> new ApplicationException.EntryNotFoundException(role.getEntryName()));

        modelMapper.map(request, existingTeacher);
        Teacher updatedTeacher = teacherRepository.save(existingTeacher);

        return modelMapper.map(updatedTeacher, PersonDto.class);
    }

    @Override
    public void deletePerson(int id) {
        teacherRepository.findById(id).ifPresentOrElse(
                teacherRepository::delete,
                () -> new ApplicationException.EntryNotFoundException(role.getEntryName())
        );
    }
}
