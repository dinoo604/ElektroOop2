package com.elektrotechniek.jpatest.backend.repositories;

import com.elektrotechniek.jpatest.backend.Vak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VakRepository extends JpaRepository<Vak, Integer> {
    @Query(value = "SELECT idvak FROM elektrotechniek.vak WHERE vak_naam LIKE CONCAT('%', :naam, '%')", nativeQuery = true)
    List<Integer> getParticularVakId(@Param("naam") String naam);
}
