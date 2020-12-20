package com.elektrotechniek.jpatest.backend.controllers;

import com.elektrotechniek.jpatest.backend.Rapport;
import com.elektrotechniek.jpatest.backend.repositories.RapportRepository;
import com.elektrotechniek.jpatest.backend.repositories.StudentRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RapportController {
    private List<Integer> integerBuffer;
    private String studNoBuffer;
    private final RapportRepository rapportRepository;
    private final StudentRepository studentRepository;

    RapportController(RapportRepository rapportRepository, StudentRepository studentRepository){
        this.rapportRepository = rapportRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/rapport")
    List<Rapport> allRapport(@RequestParam(value = "student", required = false, defaultValue = "") String studentNaam,
                             @RequestParam(value = "jaar", required = false, defaultValue = "") String jaar,
                             @RequestParam(value = "vak", required = false, defaultValue = "") String vak){
        integerBuffer = studentRepository.selectParticularStudNo(studentNaam);
        if(integerBuffer.size()>1){
            studNoBuffer = "";
        } else{
            studNoBuffer = integerBuffer.get(0).toString();
        }
        return rapportRepository.rapportByStudentAndYear(studNoBuffer, jaar, vak);
    }


    @GetMapping(value = "rapport/{id}")
    List<Rapport> singleRapport(@PathVariable Integer id){
        return rapportRepository.rapportByStudId(id);
    }
}
