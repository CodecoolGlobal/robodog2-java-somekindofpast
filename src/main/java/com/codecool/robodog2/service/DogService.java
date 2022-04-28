package com.codecool.robodog2.service;

import com.codecool.robodog2.DTO.DogDTO;
import com.codecool.robodog2.dao.DogDAO;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DogService {
    private DogDAO dogRepository;

    @Autowired
    public DogService(@Qualifier("dogJdbcDao") DogDAO dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Dog createRandomDog() {
        Dog newDog = new Dog();
        newDog.setAge(new Random().nextInt(15)+1);
        newDog.setBreed(Breed.values()[new Random().nextInt(Breed.values().length)]);
        newDog.setName("Random_Name_" + (char)(new Random().nextInt(25)+65));
        return newDog;
    }

    public void addDog(DogDTO dog) {
        dogRepository.addDog(new Dog(dog.getBreed(), dog.getName(), dog.getAge()));
    }

    public long addRandomDog() {
        return dogRepository.addDogAndReturnId(createRandomDog());
    }

    public List<Dog> listAllDogs() {
        return dogRepository.listDogs();
    }

    public Dog getDogById(long id) {
        return dogRepository.getDog(id);
    }

    public void updateDog(long id, DogDTO dog) {
        dogRepository.updateDog(new Dog(dog.getBreed(), dog.getName(), dog.getAge()), id);
    }

    public void deleteDog(long id) {
        dogRepository.deleteDog(id);
    }
}
