package com.codecool.robodog2.dao;

import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;

import java.util.List;

public interface PedigreeDAO {

    void addPedigree(Pedigree pedigree);

    List<Pedigree> listPedigrees();

    Pedigree getPedigree(long id);

    void updatePedigree(Pedigree pedigree, long id);

    void deletePedigree(long id);

    Pedigree getPedigreeByDogId(long dogId);

    public List<Dog> listDogSiblings(long dogId);
}
