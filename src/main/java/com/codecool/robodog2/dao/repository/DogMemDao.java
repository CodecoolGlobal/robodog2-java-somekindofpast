package com.codecool.robodog2.dao.repository;

import com.codecool.robodog2.IdGenerator;
import com.codecool.robodog2.dao.DogDAO;
import com.codecool.robodog2.model.Dog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DogMemDao implements DogDAO {
    private List<Dog> dogList = new ArrayList<>();

    @Override
    public void addDog(Dog dog) {
        dog.setId(IdGenerator.getId());
        dogList.add(dog);
    }

    @Override
    public List<Dog> listDogs() {
        return dogList;
    }

    @Override
    public Dog getDog(long id) {
        return dogList.stream()
                .filter(dog -> dog.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public void updateDog(Dog dog, long id) {
        Dog dogToBeUpdated = getDog(id);
        dogToBeUpdated.setAge(dog.getAge());
        dogToBeUpdated.setBreed(dog.getBreed());
        dogToBeUpdated.setName(dog.getName());
    }

    @Override
    public void deleteDog(long id) {
        dogList.removeIf(dog -> dog.getId() == id);
    }

    @Override
    public long addDogAndReturnId(Dog dog) {
        addDog(dog);
        return dog.getId();
    }
}
