package com.codecool.robodog2;

import com.codecool.robodog2.DTO.DogDTO;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import com.codecool.robodog2.model.Trick;
import com.codecool.robodog2.service.DogService;
import com.codecool.robodog2.service.SkillService;
import com.codecool.robodog2.service.TrickService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Robodog2Application {

    private static DogService dogService;
    private static TrickService trickService;
    private static SkillService skillService;

    public Robodog2Application(DogService dogService, TrickService trickService, SkillService skillService) {
        this.dogService = dogService;
        this.trickService = trickService;
        this.skillService = skillService;
    }

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Robodog2Application.class, args);

        /*dogService.addDog(new DogDTO(Breed.SPANIEL, "Dude", 8));
        System.out.println("\nDog 6 added. The name is Dude");

        System.out.println("\nDogs:--------------------------------");
        List<Dog> dogs = dogService.listAllDogs();
        for (Dog dog : dogs) {
            System.out.println("id:" + dog.getId() + " breed:" + dog.getBreed() + " age:" + dog.getAge() + " name:" + dog.getName());
        }

        System.out.println("\nThe name of the dog with id 3 is: " + dogService.getDogById(3).getName());
        dogService.updateDog(2, new DogDTO(Breed.BULLDOG, "Dieter", 7));
        System.out.println("Updated dog 2. New name is: " + dogService.getDogById(2).getName());
        System.out.println("New random dog created with an id of:" + dogService.addRandomDog());
        dogService.deleteDog(4);
        System.out.println("\nDeleted dog 4. New list of dogs:");
        dogs = dogService.listAllDogs();
        for (Dog dog : dogs) {
            System.out.println("id:" + dog.getId() + " breed:" + dog.getBreed() + " age:" + dog.getAge() + " name:" + dog.getName());
        }

        trickService.deleteTrick(3);
        System.out.println("\nDeleted trick 3. New list of tricks:");
        List<Trick> tricks = trickService.listAllTricks();
        for (Trick trick : tricks) {
            System.out.println("id:" + trick.getId() + " name:" + trick.getName());
        }

        skillService.deleteSkill(3);
        System.out.println("\nDeleted skill 3. New list of skills:");
        List<Skill> skills = skillService.listAllSkills();
        for (Skill skill : skills) {
            System.out.println("id:" + skill.getId() + " dog_id:" + skill.getDogId() + " trick_id:" + skill.getTrickId() + " level:" + skill.getLevel());
        }

        System.out.println("\nList of dogs according to trick_id 3:");
        List<Dog> dogs = skillService.listDogsByTrickId(3);
        for (Dog dog : dogs) {
            System.out.println("id:" + dog.getId() + " breed:" + dog.getBreed() + " age:" + dog.getAge() + " name:" + dog.getName());
        }

        Optional<Skill> optionalSkill = skillService.getSkill(3, 2);
        if(optionalSkill.isPresent()) {
            System.out.println("\nFound skill:");
            Skill skill = optionalSkill.get();
            System.out.println("id:" + skill.getId() + " dog_id:" + skill.getDogId() + " trick_id:" + skill.getTrickId() + " level:" + skill.getLevel());
        } else {
            System.out.println("\nSkill not found.");
        }*/
    }

}
