package com.elektrotechniek.jpatest.backend.repositories;

import com.elektrotechniek.jpatest.backend.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RapportRepository extends JpaRepository<Rapport, Integer> {
    @Query(value = "SELECT * FROM elektrotechniek.rapport WHERE student_studentennummer = :num", nativeQuery = true)
    List<Rapport> rapportByStudId(@Param("num") Integer studNummer);

    //@Query(value = "SELECT * FROM elektrotechniek.rapport WHERE student_studentennummer = :num ")

}
