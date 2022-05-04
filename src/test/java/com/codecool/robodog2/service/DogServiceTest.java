package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.repository.DogJdbcDao;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DogServiceTest {

    private final long      momId       = 1;
    private final Breed     momBreed    = Breed.BULLDOG;
    private final String    momName     = "TEST_MOM_DOG_NAME";
    private final int       momAge      = 5;

    private final long      dadId       = 2;
    private final Breed     dadBreed    = Breed.SPANIEL;
    private final String    dadName     = "TEST_DAD_DOG_NAME";
    private final int       dadAge      = 3;

    private final long      puppy1Id    = 3;
    private final int       puppy1Age   = 0;

    private final Breed     randomBreed = Breed.DACHSHUND;
    private final String    randomName  = "Random_Name_Z";
    private final int       randomAge   = 8;

    @Test
    public void test_createPuppy() {
        DogJdbcDao dogJdbcDao = mock(DogJdbcDao.class);
        when(dogJdbcDao.getDog(momId)).thenReturn(new Dog(momBreed, momName, momAge));
        when(dogJdbcDao.getDog(dadId)).thenReturn(new Dog(dadBreed, dadName, dadAge));
        when(dogJdbcDao.addDogAndReturnId(any())).thenReturn(puppy1Id);
        when(dogJdbcDao.getDog(puppy1Id)).thenReturn(new Dog(randomBreed, randomName, randomAge));

        DogService dogService = new DogService(dogJdbcDao);
        Dog testDog = dogService.createPuppy(1, 2);

        assertTrue(Arrays.asList(momBreed, dadBreed).contains(testDog.getBreed()));
        assertEquals(randomName, testDog.getName());
        assertEquals(puppy1Age, testDog.getAge());

        verify(dogJdbcDao).getDog(momId);
        verify(dogJdbcDao).getDog(dadId);
        verify(dogJdbcDao).addDogAndReturnId(any());
        verify(dogJdbcDao).getDog(puppy1Id);
    }
}
