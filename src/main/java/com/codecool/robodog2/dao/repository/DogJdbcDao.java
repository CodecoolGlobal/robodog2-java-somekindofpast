package com.codecool.robodog2.dao.repository;

import com.codecool.robodog2.dao.DogDAO;
import com.codecool.robodog2.dao.mapper.DogMapper;
import com.codecool.robodog2.model.Dog;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DogJdbcDao implements DogDAO {

    private JdbcTemplate jdbcTemplate;

    public DogJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addDog(Dog dog) {
        String query = "INSERT INTO dog (breed, age, name) VALUES (?,?,?)";
        jdbcTemplate.update(query, dog.getBreed().toString(), dog.getAge(), dog.getName());
    }

    @Override
    public List<Dog> listDogs() {
        String query = "SELECT * FROM dog";
        return jdbcTemplate.query(query, new DogMapper());
    }

    @Override
    public Dog getDog(long id) {
        String query = "SELECT * FROM dog WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new DogMapper(), id);
    }

    @Override
    public void updateDog(Dog dog, long id) {
        String query = "UPDATE dog SET breed = ?, age = ?, name = ? WHERE id = ?";
        jdbcTemplate.update(query, dog.getBreed().toString(), dog.getAge(), dog.getName(), id);
    }

    @Override
    public void deleteDog(long id) {
        String query = "DELETE FROM dog WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public long addDogAndReturnId(Dog dog) {
        addDog(dog);
        List<Dog> dogs = listDogs();
        return dogs.get(dogs.size()-1).getId();
    }
}
