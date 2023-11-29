package com.softgen.schooldms.service;

import com.softgen.schooldms.model.dto.request.AddStudentsRequest;
import com.softgen.schooldms.model.dto.request.AssignTeacherRequest;
import com.softgen.schooldms.model.dto.request.CreateGroupRequest;
import com.softgen.schooldms.model.dto.response.GroupResponse;

public interface GroupService {

    GroupResponse searchGroup(int groupNumber);

    GroupResponse createGroup(CreateGroupRequest request);

    GroupResponse modifyGroup(CreateGroupRequest request);

    void deleteGroup(int classId);

    void assignTeacher(int groupNumber, AssignTeacherRequest request);

    void addStudents(int groupNumber, AddStudentsRequest request);

}
