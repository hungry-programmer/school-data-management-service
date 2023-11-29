package com.softgen.schooldms.service;

import com.softgen.schooldms.model.dto.AddStudentsDto;
import com.softgen.schooldms.model.dto.AssignTeacherDto;
import com.softgen.schooldms.model.dto.GroupDto;

public interface GroupService {

    GroupDto searchGroup(int groupNumber);

    GroupDto createGroup(GroupDto request);

    GroupDto modifyGroup(GroupDto request);

    void deleteGroup(int classId);

    void assignTeacher(int groupId, AssignTeacherDto request);

    void addStudents(int groupId, AddStudentsDto request);

}
