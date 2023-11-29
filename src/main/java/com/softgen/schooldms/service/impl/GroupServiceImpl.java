package com.softgen.schooldms.service.impl;

import com.softgen.schooldms.exception.ApplicationException;
import com.softgen.schooldms.model.dto.AddStudentsDto;
import com.softgen.schooldms.model.dto.AssignTeacherDto;
import com.softgen.schooldms.model.dto.GroupDto;
import com.softgen.schooldms.model.entity.Group;
import com.softgen.schooldms.repository.GroupRepository;
import com.softgen.schooldms.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;
    private final String GROUP = "group";


    @Override
    public GroupDto searchGroup(int groupNumber) {
        Optional<Group> optionalGroup = groupRepository.findByGroupNumber(groupNumber);

        return optionalGroup.map(g -> modelMapper.map(g, GroupDto.class))
                .orElseThrow(() -> new ApplicationException.EntryNotFoundException(GROUP));
    }

    @Override
    public GroupDto createGroup(GroupDto request) {
        Group group = modelMapper.map(request, Group.class);
        Group savedGroup = groupRepository.save(group);

        return modelMapper.map(savedGroup, GroupDto.class);
    }


    @Override
    public GroupDto modifyGroup(GroupDto request) {
        Group existingGroup = groupRepository.findById(request.getId())
                .orElseThrow(() -> new ApplicationException.EntryNotFoundException(GROUP));

        modelMapper.map(request, existingGroup);
        Group updatedGroup = groupRepository.save(existingGroup);

        return modelMapper.map(updatedGroup, GroupDto.class);
    }

    @Override
    public void deleteGroup(int id) {
        groupRepository.findById(id).ifPresentOrElse(
                groupRepository::delete,
                () -> new ApplicationException.EntryNotFoundException(GROUP)
        );
    }

    @Override
    public void assignTeacher(int groupId, AssignTeacherDto request) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ApplicationException.EntryNotFoundException(GROUP));


    }

    @Override
    public void addStudents(int groupId, AddStudentsDto request) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ApplicationException.EntryNotFoundException(GROUP));
    }
}
