package com.elektrotechniek.jpatest.backend.controllers;

import com.elektrotechniek.jpatest.backend.Rapport;
import com.elektrotechniek.jpatest.backend.repositories.RapportRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RapportController {
    private final RapportRepository rapportRepository;

    RapportController(RapportRepository rapportRepository){
        this.rapportRepository = rapportRepository;
    }

    @GetMapping("/rapport")
    List<Rapport> allRapport(@RequestParam(value = "student", required = false, defaultValue = "") String studentNum,
                             @RequestParam(value = "jaar", required = false, defaultValue = "") String jaar){
        return rapportRepository.rapportByStudentAndYear(studentNum, jaar);
    }


    @GetMapping(value = "rapport/{id}")
    List<Rapport> singleRapport(@PathVariable Integer id){
        return rapportRepository.rapportByStudId(id);
    }
}
