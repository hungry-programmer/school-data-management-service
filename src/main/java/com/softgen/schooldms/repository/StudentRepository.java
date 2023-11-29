package com.softgen.schooldms.repository;

import com.softgen.schooldms.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByFirstnameContainingAndLastnameContainingAndIdNumberAndBirthDate(
            String firstname,
            String lastname,
            Long idNumber,
            LocalDateTime birthDate
    );

}