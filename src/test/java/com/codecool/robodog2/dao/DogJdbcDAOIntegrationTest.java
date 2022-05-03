package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.DogMapper;
import com.codecool.robodog2.dao.repository.DogJdbcDao;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Use the schema built in this .sql file put in resources
@Sql(scripts = {"classpath:schema.sql"})
@ComponentScan
@SpringBootTest
//As the context becomes dirty - database modifications happen, etc. - this method restores it to the original state
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DogJdbcDAOIntegrationTest {

    @Autowired
    private DogJdbcDao dogJdbcDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final long    id1     = 1;
    private final Breed   breed1  = Breed.BULLDOG;
    private final String  name1   = "TEST_DOG_1_NAME";
    private final int     age1    = 5;

    private final long    id2     = 2;
    private final Breed   breed2  = Breed.SPANIEL;
    private final String  name2   = "TEST_DOG_2_NAME";
    private final int     age2    = 3;

    @Test
    public  void test_addDog() {
        dogJdbcDao.addDog(new Dog(breed1, name1, age1));
        Dog testDog = jdbcTemplate.queryForObject("SELECT * FROm dog WHERE dog.id = ?", new DogMapper(), id1);

        assertEquals(id1, testDog.getId());
        assertEquals(breed1.toString(), testDog.getBreed().toString());
        assertEquals(name1, testDog.getName());
        assertEquals(age1, testDog.getAge());
    }

    @Test
    public  void test_listDogs() {
        jdbcTemplate.update("INSERT INTO dog (breed, age, name) VALUES (?,?,?)", breed1.toString(), age1, name1);
        jdbcTemplate.update("INSERT INTO dog (breed, age, name) VALUES (?,?,?)", breed2.toString(), age2, name2);
        List<Dog> testDogs = dogJdbcDao.listDogs();

        assertEquals(id1, testDogs.get(0).getId());
        assertEquals(breed1.toString(), testDogs.get(0).getBreed().toString());
        assertEquals(name1, testDogs.get(0).getName());
        assertEquals(age1, testDogs.get(0).getAge());

        assertEquals(id2, testDogs.get(1).getId());
        assertEquals(breed2.toString(), testDogs.get(1).getBreed().toString());
        assertEquals(name2, testDogs.get(1).getName());
        assertEquals(age2, testDogs.get(1).getAge());
    }

    @Test
    public void test_getDog() {
        jdbcTemplate.update("INSERT INTO dog (breed, age, name) VALUES (?,?,?)", breed1.toString(), age1, name1);
        Dog testDog = dogJdbcDao.getDog(id1);

        assertEquals(id1, testDog.getId());
        assertEquals(breed1.toString(), testDog.getBreed().toString());
        assertEquals(name1, testDog.getName());
        assertEquals(age1, testDog.getAge());
    }

    @Test
    public void test_updateDog() {
        jdbcTemplate.update("INSERT INTO dog (breed, age, name) VALUES (?,?,?)", breed1.toString(), age1, name1);
        dogJdbcDao.updateDog(new Dog(breed2, name2, age2), id1);
        Dog testDog = jdbcTemplate.queryForObject("SELECT * FROM dog WHERE id = ?", new DogMapper(), id1);

        assertEquals(id1, testDog.getId());
        assertEquals(breed2.toString(), testDog.getBreed().toString());
        assertEquals(name2, testDog.getName());
        assertEquals(age2, testDog.getAge());
    }

    @Test
    public void test_deleteDog() {
        jdbcTemplate.update("INSERT INTO dog (breed, age, name) VALUES (?,?,?)", breed1.toString(), age1, name1);
        jdbcTemplate.update("INSERT INTO dog (breed, age, name) VALUES (?,?,?)", breed2.toString(), age2, name2);
        dogJdbcDao.deleteDog(id1);
        List<Dog> testDogs = jdbcTemplate.query("SELECT * FROM dog", new DogMapper());

        assertEquals(1, testDogs.size());
        assertEquals(id2, testDogs.get(0).getId());
        assertEquals(breed2.toString(), testDogs.get(0).getBreed().toString());
        assertEquals(name2, testDogs.get(0).getName());
        assertEquals(age2, testDogs.get(0).getAge());
    }
}
