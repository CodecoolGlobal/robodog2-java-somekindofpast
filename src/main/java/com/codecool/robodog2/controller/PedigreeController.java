package com.codecool.robodog2.controller;

import com.codecool.robodog2.DTO.DogDTO;
import com.codecool.robodog2.DTO.PedigreeDTO;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import com.codecool.robodog2.service.DogService;
import com.codecool.robodog2.service.PedigreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/dog")
public class PedigreeController {

    private PedigreeService pedigreeService;
    private DogService dogService;

    @Autowired
    public PedigreeController(PedigreeService pedigreeService, DogService dogService) {
        this.pedigreeService = pedigreeService;
        this.dogService = dogService;
    }

    @GetMapping("/{dogId}/pedigree")
    public Pedigree getPedigreeByDogId(@PathVariable("dogId") long dogId) {
        return pedigreeService.getPedigreeByDogId(dogId);
    }

    @PostMapping("/{dogId}/pedigree")
    public void addPedigree(@PathVariable("dogId") long dogId, @RequestBody PedigreeDTO pedigree) {
        pedigreeService.addPedigree(pedigree);
    }

    @GetMapping("/puppy")
    public Dog createPuppy(@RequestBody Map<String, Long> json) {
        long momId = json.get("momId");
        long dadId = json.get("dadId");
        Dog mom = dogService.getDogById(momId);
        Dog dad = dogService.getDogById(dadId);
        long puppyId = dogService.addRandomDog();
        Dog puppy = dogService.getDogById(puppyId);
        puppy.setBreed(new Random().nextInt() % 2 == 0 ? mom.getBreed() : dad.getBreed());
        puppy.setAge(0);
        dogService.updateDog(puppyId, new DogDTO(puppy.getBreed(), puppy.getName(), puppy.getAge()));
        pedigreeService.addPedigree(new PedigreeDTO(momId, dadId, puppyId));
        return puppy;
    }

    @GetMapping("/{dogId}/pedigree/mom")
    public Dog getMom(@PathVariable("dogId") long dogId) {
        Pedigree pedigree = pedigreeService.getPedigreeByDogId(dogId);
        return dogService.getDogById(pedigree.getMomId());
    }

    @GetMapping("/{dogId}/pedigree/dad")
    public Dog getDad(@PathVariable("dogId") long dogId) {
        Pedigree pedigree = pedigreeService.getPedigreeByDogId(dogId);
        return dogService.getDogById(pedigree.getDadId());
    }
}
