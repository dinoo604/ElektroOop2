package com.elektrotechniek.jpatest.backend.repositories;

import com.elektrotechniek.jpatest.backend.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "SELECT * FROM elektrotechniek.student WHERE (CONCAT (naam, ' ', achternaam) LIKE CONCAT('%', :search, '%')" +
            " OR studentennummer LIKE CONCAT(:search, '%'))", nativeQuery = true)
    List<Student> search(@Param("search") String searchText);

    @Query(value = "SELECT student.studentennummer FROM elektrotechniek.student WHERE " +
            "CONCAT (naam, ' ', achternaam) LIKE CONCAT('%', :naam, '%')", nativeQuery = true)
    List<Integer> selectParticularStudNo(@Param("naam") String naam);
}
