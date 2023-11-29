package com.softgen.schooldms.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    STUDENT("student"),
    TEACHER("teacher");

    private final String entryName;

}
