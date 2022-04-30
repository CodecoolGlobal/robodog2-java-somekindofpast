package com.codecool.robodog2.controller;

import com.codecool.robodog2.DTO.SkillDTO;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import com.codecool.robodog2.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skill")
public class SkillController {
    private SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.listAllSkills();
    }

    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable("id") long id) {
        return skillService.getSkillById(id);
    }

    @PostMapping("/new")
    public void addSkill(@RequestBody SkillDTO skill) {
        skillService.addSkill(skill);
    }

    @PutMapping("/update/{id}")
    public void updateSkill(@PathVariable("id") long id, @RequestBody SkillDTO skill) {
        skillService.updateSkill(id, skill);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSkill(@PathVariable("id") long id) {
        skillService.deleteSkill(id);
    }

    @GetMapping("/dogs-by-trick/{trickId}")
    public List<Dog> listDogsByTrickId(@PathVariable("trickId") long trickId) {
        return skillService.listDogsByTrickId(trickId);
    }

    @GetMapping("/{dogId}/{trickId}")
    public Skill getSkill(@PathVariable("dogId") long dogId, @PathVariable("trickId") long trickId) {
        return skillService.getSkill(dogId, trickId).orElse(null);
    }
}
