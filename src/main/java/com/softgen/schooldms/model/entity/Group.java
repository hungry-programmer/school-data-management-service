package com.softgen.schooldms.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "'group'")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq_gen")
    @SequenceGenerator(name = "group_seq_gen", sequenceName = "group_id_seq",  allocationSize = 1)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "group_number", unique = true)
    private int groupNumber;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students;

}
