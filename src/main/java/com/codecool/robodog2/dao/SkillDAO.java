package com.codecool.robodog2.dao;

import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillDAO {

    void addSkill(Skill skill);

    List<Skill> listSkills();

    Skill getSkill(long id);

    void updateSkill(Skill skill, long id);

    void deleteSkill(long id);

    List<Dog> listDogsByTrickId(long trickId);

    Optional<Skill> getSkill(long dogId, long trickId);

    int checkSkillLevel(String trickName, long dogId);

    void increaseSkillLevel(String trickName, long dogId);
}
