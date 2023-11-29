package com.softgen.schooldms.repository;

import com.softgen.schooldms.model.entity.Teacher;
import com.softgen.schooldms.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    List<Teacher> findByFirstnameContainingAndLastnameContainingAndIdNumberAndBirthDate(
            String firstname,
            String lastname,
            Long idNumber,
            LocalDateTime birthDate
    );

}