package com.softgen.schooldms.service.impl;

import com.softgen.schooldms.exception.ApplicationException;
import com.softgen.schooldms.model.dto.request.AddStudentsRequest;
import com.softgen.schooldms.model.dto.request.AssignTeacherRequest;
import com.softgen.schooldms.model.dto.request.CreateGroupRequest;
import com.softgen.schooldms.model.dto.response.GroupResponse;
import com.softgen.schooldms.model.entity.Group;
import com.softgen.schooldms.model.entity.Student;
import com.softgen.schooldms.model.entity.Teacher;
import com.softgen.schooldms.repository.GroupRepository;
import com.softgen.schooldms.repository.StudentRepository;
import com.softgen.schooldms.repository.TeacherRepository;
import com.softgen.schooldms.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final String GROUP = "group";
    private final String TEACHER = "teacher";


    @Override
    public GroupResponse searchGroup(int groupNumber) {
        Optional<Group> optionalGroup = groupRepository.findByGroupNumber(groupNumber);

        return optionalGroup.map(g -> modelMapper.map(g, GroupResponse.class))
                .orElseThrow(() -> new ApplicationException.EntryNotFoundException(GROUP));
    }

    @Override
    public GroupResponse createGroup(CreateGroupRequest request) {
        Group group = modelMapper.map(request, Group.class);
        Group savedGroup = groupRepository.save(group);

        return modelMapper.map(savedGroup, GroupResponse.class);
    }


    @Override
    public GroupResponse modifyGroup(CreateGroupRequest request) {
        Group existingGroup = groupRepository.findById(request.getId())
                .orElseThrow(() -> new ApplicationException.EntryNotFoundException(GROUP));

        modelMapper.map(request, existingGroup);
        Group updatedGroup = groupRepository.save(existingGroup);

        return modelMapper.map(updatedGroup, GroupResponse.class);
    }

    @Override
    public void deleteGroup(int id) {
        groupRepository.findById(id).ifPresentOrElse(
                groupRepository::delete,
                () -> new ApplicationException.EntryNotFoundException(GROUP)
        );
    }

    @Override
    public void assignTeacher(int groupNumber, AssignTeacherRequest request) {
        Group group = groupRepository.findByGroupNumber(groupNumber)
                .orElseThrow(() -> new ApplicationException.EntryNotFoundException(GROUP));

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new ApplicationException.EntryNotFoundException(TEACHER));
        group.setTeacher(teacher);

        groupRepository.save(group);
    }

    @Override
    public void addStudents(int groupNumber, AddStudentsRequest request) {
        Group group = groupRepository.findByGroupNumber(groupNumber)
                .orElseThrow(() -> new ApplicationException.EntryNotFoundException(GROUP));

        List<Student> students = studentRepository.findAllById(request.getStudentIds());
        if (students.isEmpty()) {
            return;
        }

        group.setStudents(students);
        groupRepository.save(group);

        students.stream()
                .peek(student -> student.setGroup(group))
                .forEach(studentRepository::save);
    }
}
