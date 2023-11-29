package com.softgen.schooldms.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AddStudentsRequest {

    @NotEmpty
    private List<@NotNull Integer> studentIds;

}
