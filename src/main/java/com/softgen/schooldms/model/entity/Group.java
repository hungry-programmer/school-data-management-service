package com.softgen.schooldms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "group_table")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_table_seq_gen")
    @SequenceGenerator(name = "group_table_seq_gen", sequenceName = "group_table_id_seq",  allocationSize = 1)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "group_number", unique = true)
    private int groupNumber;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.PERSIST)
    private List<Student> students;

}
