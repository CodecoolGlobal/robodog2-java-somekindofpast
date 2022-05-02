package com.codecool.robodog2.dao.repository;

import com.codecool.robodog2.dao.PedigreeDAO;
import com.codecool.robodog2.dao.mapper.DogMapper;
import com.codecool.robodog2.dao.mapper.PedigreeMapper;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedigreeJdbcDao implements PedigreeDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PedigreeJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addPedigree(Pedigree pedigree) {
        String query = "INSERT INTO pedigree (mom_id, dad_id, puppy_id) VALUES (?,?,?)";
        jdbcTemplate.update(query, pedigree.getMomId(), pedigree.getDadId(), pedigree.getPuppyId());
    }

    @Override
    public List<Pedigree> listPedigrees() {
        String query = "SELECT * FROM pedigree";
        return jdbcTemplate.query(query, new PedigreeMapper());
    }

    @Override
    public Pedigree getPedigree(long id) {
        String query = "SELECT * FROM pedigree WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new PedigreeMapper(), id);
    }

    @Override
    public void updatePedigree(Pedigree pedigree, long id) {
        String query = "UPDATE pedigree SET mom_id = ?, dad_id = ?, puppy_id = ? WHERE id = ?";
        jdbcTemplate.update(query, pedigree.getMomId(), pedigree.getDadId(), pedigree.getPuppyId(), id);
    }

    @Override
    public void deletePedigree(long id) {
        String query = "DELETE FROM pedigree WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public Pedigree getPedigreeByDogId(long dogId) {
        String query = "SELECT * FROM pedigree WHERE puppy_id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new PedigreeMapper(), dogId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Dog> listDogSiblings(long dogId) {
        Pedigree pedigree = getPedigreeByDogId(dogId);
        String query = "SELECT * FROM dog " +
                "INNER JOIN pedigree ON dog.id = pedigree.puppy_id " +
                "WHERE dog.id != ? AND (pedigree.mom_id = ? OR pedigree.dad_id = ?)";
        return jdbcTemplate.query(query, new DogMapper(), dogId, pedigree.getMomId(), pedigree.getDadId());
    }
}
