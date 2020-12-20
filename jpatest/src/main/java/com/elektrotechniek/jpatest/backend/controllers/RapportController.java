package com.elektrotechniek.jpatest.backend.controllers;

import com.elektrotechniek.jpatest.backend.Rapport;
import com.elektrotechniek.jpatest.backend.repositories.RapportRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RapportController {
    private final RapportRepository rapportRepository;

    RapportController(RapportRepository rapportRepository){
        this.rapportRepository = rapportRepository;
    }

    @GetMapping("/rapport")
    List<Rapport> allRapport(){

        return rapportRepository.findAll();
    }

    @GetMapping(value = "rapport/{id}")
    List<Rapport> singleRapport(@PathVariable Integer id){
        return rapportRepository.rapportByStudId(id);
    }
}
