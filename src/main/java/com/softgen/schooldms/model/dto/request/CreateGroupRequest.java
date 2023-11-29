package com.softgen.schooldms.model.dto.request;

import lombok.Data;

@Data
public class CreateGroupRequest {

    private Integer id;
    private String name;
    private int groupNumber;

}
