package com.softgen.schooldms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq_gen")
    @SequenceGenerator(name = "student_seq_gen", sequenceName = "student_id_seq", allocationSize = 1)
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
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "group_id")
    private Group group;

}
