package com.codecool.robodog2.service;

import com.codecool.robodog2.DTO.SkillDTO;
import com.codecool.robodog2.dao.SkillDAO;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {
    private SkillDAO skillRepository;

    @Autowired
    public SkillService(SkillDAO skillRepository) {
        this.skillRepository = skillRepository;
    }

    public void addSkill(SkillDTO skill) {
        skillRepository.addSkill(new Skill(skill.getDogId(), skill.getTrickId(), skill.getLevel()));
    }

    public List<Skill> listAllSkills() {
        return skillRepository.listSkills();
    }

    public Skill getSkillById(long id) {
        return skillRepository.getSkill(id);
    }

    public void updateSkill(long id, SkillDTO skill) {
        skillRepository.updateSkill(new Skill(skill.getDogId(), skill.getTrickId(), skill.getLevel()), id);
    }

    public void deleteSkill(long id) {
        skillRepository.deleteSkill(id);
    }

    public List<Dog> listDogsByTrickId(long trickId) {
        return skillRepository.listDogsByTrickId(trickId);
    }

    public Optional<Skill> getSkill(long dogId, long trickId) {
        return skillRepository.getSkill(dogId, trickId);
    }

    public int checkSkillLevel(String trickName, long dogId) {
        return skillRepository.checkSkillLevel(trickName, dogId);
    }

    public void increaseSkillLevel(String trickName, long dogId) {
        skillRepository.increaseSkillLevel(trickName, dogId);
    }
}
