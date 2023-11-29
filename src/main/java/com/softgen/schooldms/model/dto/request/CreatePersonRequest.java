package com.softgen.schooldms.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreatePersonRequest {

    private Integer id;

    @Size(max = 125, message = "Firstname must not exceed 125 characters")
    private String firstname;

    @Size(max = 125, message = "Lastname must not exceed 125 characters")
    private String lastname;

    private Long idNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime birthDate;

    @Email(message = "Invalid email format")
    private String mail;

}
