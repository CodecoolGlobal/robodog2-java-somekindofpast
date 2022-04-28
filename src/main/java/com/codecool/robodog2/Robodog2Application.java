package com.codecool.robodog2;

import com.codecool.robodog2.DTO.DogDTO;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.service.DogService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class Robodog2Application {

    private static DogService dogService;

    public Robodog2Application(DogService dogService) {
        this.dogService = dogService;
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
        }*/
    }

}
