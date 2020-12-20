package com.elektrotechniek.jpatest.backend.controllers;

import com.elektrotechniek.jpatest.backend.Vak;
import com.elektrotechniek.jpatest.backend.exceptions.VakNotFoundException;
import com.elektrotechniek.jpatest.backend.repositories.VakRepository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
public class VakController {
    private final VakRepository vakRepository;

    public VakController(VakRepository vakRepository) {
        this.vakRepository = vakRepository;
    }

    @GetMapping("/vak")
    List<Vak> allVak(){
        return vakRepository.findAll();
    }

    @GetMapping("/vak/{id}")
    Vak oneVak(@PathVariable Integer id){
        return vakRepository.findById(id).
                orElseThrow(()-> new VakNotFoundException(id));
    }

    @PostMapping("/vak")
    Vak newVak(@RequestBody Vak newVak){
        return vakRepository.save(newVak);
    }


    @PutMapping("/vak/{id}")
    Vak editVak(@RequestBody Vak newVak, @PathVariable Integer id){
        return vakRepository.findById(id).map(
                vak-> {
                    vak.setVak_naam(newVak.getVak_naam());
                    vak.setSemester(newVak.getSemester());
                    vak.setStudiepunten(newVak.getStudiepunten());
                    vak.setVak_orientatie(newVak.getVak_orientatie());
                    return vakRepository.save(vak);
                }).orElseGet(() -> {
                    newVak.setIdvak(id);
                    return vakRepository.save(newVak);
                });
    }

    @DeleteMapping("vak/{id}")
    void deleteVak(@PathVariable Integer id){
        vakRepository.deleteById(id);
    }
}
