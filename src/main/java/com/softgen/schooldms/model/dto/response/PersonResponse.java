package com.softgen.schooldms.model.dto.response;

import com.softgen.schooldms.model.entity.Group;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PersonResponse {

    private int id;
    private String firstname;
    private String lastname;
    private Long idNumber;
    private String mail;
    private LocalDateTime birthDate;

}
