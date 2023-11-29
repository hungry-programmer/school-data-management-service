package com.softgen.schooldms.service;

import com.softgen.schooldms.model.dto.GroupDto;

public interface GroupService {
    GroupDto searchGroup(int groupNumber);
    GroupDto createGroup(GroupDto request);
    GroupDto modifyGroup(GroupDto request);
    void deleteGroup(int classId);

}
