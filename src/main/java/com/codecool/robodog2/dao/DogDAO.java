package com.codecool.robodog2.dao;

import com.codecool.robodog2.model.Dog;

import java.util.List;

public interface DogDAO {

    void addDog(Dog dog);

    List<Dog> listDogs();

    Dog getDog(long id);

    void updateDog(Dog dog, long id);

    void deleteDog(long id);

    long addDogAndReturnId(Dog dog);
}
