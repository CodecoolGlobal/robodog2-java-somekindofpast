package com.codecool.robodog2.controller;

import com.codecool.robodog2.DTO.DogDTO;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dog")
public class DogController {
    private DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping
    public List<Dog> getAllDogs() {
        return dogService.listAllDogs();
    }

    @GetMapping("/{id}")
    public Dog getDogById(@PathVariable("id") long id) {
        return dogService.getDogById(id);
    }

    @GetMapping("/random")
    public Dog getRandomDog() {
        long id = dogService.addRandomDog();
        return dogService.getDogById(id);
    }

    @PostMapping("/new")
    public void addDog(@RequestBody DogDTO dog) {
        dogService.addDog(dog);
    }

    @PutMapping("/update/{id}")
    public void updateDog(@PathVariable("id") long id, @RequestBody DogDTO dog) {
        dogService.updateDog(id, dog);
    }

    @DeleteMapping("delete/{id}")
    public void deleteDog(@PathVariable("id") long id) {
        dogService.deleteDog(id);
    }
}
