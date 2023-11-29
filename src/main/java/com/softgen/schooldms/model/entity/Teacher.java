package com.softgen.schooldms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq_gen")
    @SequenceGenerator(name = "teacher_seq_gen", sequenceName = "teacher_id_seq", allocationSize = 1)
    private int id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "id_number", unique = true)
    private Long idNumber;
    @Column(name = "mail")
    private String mail;
    @Column(name = "birth_date")
    private LocalDateTime birthDate;
    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<Group> groups;

}
