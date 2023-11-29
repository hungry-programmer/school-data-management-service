package com.softgen.schooldms.model.dto.response;

import com.softgen.schooldms.model.entity.Group;
import lombok.Data;

@Data
public class StudentResponse extends PersonResponse{

    private Group group;

}
