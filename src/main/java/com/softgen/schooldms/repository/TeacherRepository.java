package com.softgen.schooldms.repository;

import com.softgen.schooldms.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    List<Teacher> findAllByFirstnameContainingOrLastnameContainingOrIdNumberOrBirthDate(
            String firstname, String lastname, Long idNumber, LocalDateTime birthDate
    );

}