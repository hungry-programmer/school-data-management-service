package com.softgen.schooldms.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PersonDto {

    private Integer id;

    @Size(max = 125, message = "Firstname must not exceed 125 characters")
    private String firstname;

    @Size(max = 125, message = "Lastname must not exceed 125 characters")
    private String lastname;

    private Long idNumber;

    private LocalDateTime birthDate;

    @Email(message = "Invalid email format")
    private String mail;

}
