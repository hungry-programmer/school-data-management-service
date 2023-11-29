package com.softgen.schooldms.model.dto.response;

import com.softgen.schooldms.model.entity.Group;
import lombok.Data;

import java.util.List;

@Data
public class TeacherResponse extends PersonResponse {

    private List<Group> groups;

}
