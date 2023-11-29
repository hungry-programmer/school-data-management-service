package com.softgen.schooldms.model.dto.response;

import com.softgen.schooldms.model.entity.Student;
import com.softgen.schooldms.model.entity.Teacher;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class GroupResponse {

    private int id;
    private String name;
    private int groupNumber;
    private Teacher teacher;
    private List<Student> students;

}
