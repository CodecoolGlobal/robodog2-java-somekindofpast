package com.codecool.robodog2.dao;

import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Use the schema built in the .sql file put in resources
@Sql(scripts = {"classpath:schema.sql"})
@ComponentScan
@SpringBootTest
//As the context becomes dirty - database modifications happen, etc. - this method restores it to the original state
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PedigreeJdbcDAOIntegrationTest {

    @Autowired
    private PedigreeDAO pedigreeDAO;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final long      momId       = 1;
    private final Breed     momBreed    = Breed.BULLDOG;
    private final String    momName     = "TEST_MOM_DOG_NAME";
    private final int       momAge      = 5;

    private final long      dadId       = 2;
    private final Breed     dadBreed    = Breed.SPANIEL;
    private final String    dadName     = "TEST_DAD_DOG_NAME";
    private final int       dadAge      = 3;

    private final long      puppy1Id    = 3;
    private final Breed     puppy1Breed = Breed.BULLDOG;
    private final String    puppy1Name  = "TEST_PUPPY_1_NAME";
    private final int       puppy1Age   = 0;

    private final long      puppy2Id    = 4;
    private final Breed     puppy2Breed = Breed.SPANIEL;
    private final String    puppy2Name  = "TEST_PUPPY_2_NAME";
    private final int       puppy2Age   = 0;

    private final long      puppy3Id    = 5;
    private final Breed     puppy3Breed = Breed.SPANIEL;
    private final String    puppy3Name  = "TEST_PUPPY_3_NAME";
    private final int       puppy3Age   = 0;

    @BeforeEach
    public void beforeAll() {
        jdbcTemplate.update("INSERT INTO dog (breed, age, name) VALUES (?,?,?)", momBreed.toString(), momAge, momName);
        jdbcTemplate.update("INSERT INTO dog (breed, age, name) VALUES (?,?,?)", dadBreed.toString(), dadAge, dadName);
        jdbcTemplate.update("INSERT INTO dog (breed, age, name) VALUES (?,?,?)", puppy1Breed.toString(), puppy1Age, puppy1Name);
        jdbcTemplate.update("INSERT INTO dog (breed, age, name) VALUES (?,?,?)", puppy2Breed.toString(), puppy2Age, puppy2Name);
        jdbcTemplate.update("INSERT INTO dog (breed, age, name) VALUES (?,?,?)", puppy3Breed.toString(), puppy3Age, puppy3Name);

        jdbcTemplate.update("INSERT INTO pedigree (mom_id, dad_id, puppy_id) VALUES (?,?,?)", momId, dadId, puppy1Id);
        jdbcTemplate.update("INSERT INTO pedigree (mom_id, dad_id, puppy_id) VALUES (?,?,?)", momId, dadId, puppy2Id);
        jdbcTemplate.update("INSERT INTO pedigree (mom_id, dad_id, puppy_id) VALUES (?,?,?)", momId, dadId, puppy3Id);
    }

    @Test
    public void test_getAllPedigrees() {
        List<Pedigree> pedigrees = pedigreeDAO.listPedigrees();

        assertEquals(3, pedigrees.size());

        assertEquals(momId, pedigrees.get(0).getMomId());
        assertEquals(dadId, pedigrees.get(0).getDadId());
        assertEquals(puppy1Id, pedigrees.get(0).getPuppyId());

        assertEquals(momId, pedigrees.get(1).getMomId());
        assertEquals(dadId, pedigrees.get(1).getDadId());
        assertEquals(puppy2Id, pedigrees.get(1).getPuppyId());

        assertEquals(momId, pedigrees.get(2).getMomId());
        assertEquals(dadId, pedigrees.get(2).getDadId());
        assertEquals(puppy3Id, pedigrees.get(2).getPuppyId());
    }

    @Test
    public void test_getMomDog() {
        Dog testDog = pedigreeDAO.getMomDog(puppy1Id);

        assertEquals(momId, testDog.getId());
        assertEquals(momBreed.toString(), testDog.getBreed().toString());
        assertEquals(momName, testDog.getName());
        assertEquals(momAge, testDog.getAge());
    }

    @Test
    public void test_getDadDog() {
        Dog testDog = pedigreeDAO.getDadDog(puppy2Id);

        assertEquals(dadId, testDog.getId());
        assertEquals(dadBreed.toString(), testDog.getBreed().toString());
        assertEquals(dadName, testDog.getName());
        assertEquals(dadAge, testDog.getAge());
    }

    @Test
    public void getSiblingDogs() {
        List<Dog> testDogs = pedigreeDAO.listDogSiblings(puppy3Id);

        assertEquals(2, testDogs.size());

        assertEquals(puppy1Id, testDogs.get(0).getId());
        assertEquals(puppy1Breed.toString(), testDogs.get(0).getBreed().toString());
        assertEquals(puppy1Name, testDogs.get(0).getName());
        assertEquals(puppy1Age, testDogs.get(0).getAge());

        assertEquals(puppy2Id, testDogs.get(1).getId());
        assertEquals(puppy2Breed.toString(), testDogs.get(1).getBreed().toString());
        assertEquals(puppy2Name, testDogs.get(1).getName());
        assertEquals(puppy2Age, testDogs.get(1).getAge());
    }
}
