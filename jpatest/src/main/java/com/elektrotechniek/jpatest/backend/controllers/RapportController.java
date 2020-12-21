package com.elektrotechniek.jpatest.backend.controllers;

import com.elektrotechniek.jpatest.backend.Rapport;
import com.elektrotechniek.jpatest.backend.repositories.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RapportController {
    private String studNoBuffer, vakNoBuffer;
    private final RapportRepository rapportRepository;
    private final StudentRepository studentRepository;
    private final VakRepository vakRepository;

    RapportController(RapportRepository rapportRepository, StudentRepository studentRepository,
                      VakRepository vakRepository){
        this.rapportRepository = rapportRepository;
        this.studentRepository = studentRepository;
        this.vakRepository = vakRepository;
    }

    @GetMapping("/rapport")
    List<Rapport> allRapport(@RequestParam(value = "student", required = false, defaultValue = "") String studentNaam,
                             @RequestParam(value = "jaar", required = false, defaultValue = "") String jaar,
                             @RequestParam(value = "vak", required = false, defaultValue = "") String vak){
        List<Integer> studentNum = studentRepository.selectParticularStudNo(studentNaam);
        List<Integer> vakId = vakRepository.getParticularVakId(vak);
        if(vakId.size() > 1){
            vakNoBuffer = "";
        } else {
            vakNoBuffer = vakId.get(0).toString();
        }
        if(studentNum.size() > 1 ){
            studNoBuffer = "";
        } else{
            studNoBuffer = studentNum.get(0).toString();
        }
        return rapportRepository.rapportByStudentAndYear(studNoBuffer, jaar, vakNoBuffer);
    }


    @GetMapping(value = "/rapport/{id}")
    List<Rapport> singleRapport(@PathVariable Integer id){
        return rapportRepository.rapportByStudId(id);
    }

    @PostMapping(value = "/rapport")
    Rapport newRapport(@RequestBody Rapport rapport){
        return rapportRepository.save(rapport);
    }

    @PutMapping(value = "/rapport/{id}")
    Rapport editRapport(@RequestBody Rapport newRapport, @PathVariable Integer id){
        return rapportRepository.findById(id).map(
                rapport -> {
                    rapport.setStudent(newRapport.getStudent());
                    rapport.setVak(newRapport.getVak());
                    rapport.setDatum(newRapport.getDatum());
                    rapport.setCijfer(newRapport.getCijfer());
                    return rapportRepository.save(rapport);
                }).orElseGet(()-> {
                    newRapport.setRapport_id(id);
                    return rapportRepository.save(newRapport);
                });
    }

    @DeleteMapping(value = "/rapport/{id}")
    void deleteRapport(@PathVariable Integer id){
        rapportRepository.deleteById(id);
    }
}
