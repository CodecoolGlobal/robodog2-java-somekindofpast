package com.codecool.robodog2.controller;

import com.codecool.robodog2.DTO.PedigreeDTO;
import com.codecool.robodog2.model.Pedigree;
import com.codecool.robodog2.service.PedigreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dog")
public class PedigreeController {

    private PedigreeService pedigreeService;

    @Autowired
    public PedigreeController(PedigreeService pedigreeService) {
        this.pedigreeService = pedigreeService;
    }

    @GetMapping("/{dogId}/pedigree")
    public Pedigree getPedigreeByDogId(@PathVariable("dogId") long dogId) {
        return pedigreeService.getPedigreeByDogId(dogId);
    }

    @PostMapping("/{dogId}/pedigree")
    public void addPedigree(@PathVariable("dogId") long dogId, @RequestBody PedigreeDTO pedigree) {
        pedigreeService.addPedigree(pedigree);
    }
}
