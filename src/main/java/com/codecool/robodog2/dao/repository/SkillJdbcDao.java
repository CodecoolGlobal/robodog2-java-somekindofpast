package com.codecool.robodog2.dao.repository;

import com.codecool.robodog2.dao.SkillDAO;
import com.codecool.robodog2.dao.mapper.DogMapper;
import com.codecool.robodog2.dao.mapper.SkillMapper;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SkillJdbcDao implements SkillDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SkillJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addSkill(Skill skill) {
        String query = "INSERT INTO skill (dog_id, trick_id, level) VALUES (?,?,?)";
        jdbcTemplate.update(query, skill.getDogId(), skill.getTrickId(), skill.getLevel());
    }

    @Override
    public List<Skill> listSkills() {
        String query = "SELECT * FROM skill";
        return jdbcTemplate.query(query, new SkillMapper());
    }

    @Override
    public Skill getSkill(long id) {
        String query = "SELECT * FROM skill WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new SkillMapper(), id);
    }

    @Override
    public void updateSkill(Skill skill, long id) {
        String query = "UPDATE skill SET dog_id = ?, trick_id = ?, level = ? WHERE id = ?";
        jdbcTemplate.update(query, skill.getDogId(), skill.getTrickId(), skill.getLevel(), id);
    }

    @Override
    public void deleteSkill(long id) {
        String query = "DELETE FROM skill WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public List<Dog> listDogsByTrickId(long trickId) {
        String query = "SELECT * FROM dog" +
                "INNER JOIN skill ON dog.id = skill.dog_id" +
                "WHERE skill.trick_id = ?";
        return jdbcTemplate.query(query, new DogMapper(), trickId);
    }

    @Override
    public Optional<Skill> getSkill(long dogId, long trickId) {
        /*try {
            String query = "SELECT * FROM skill WHERE dog_id = ? AND trick_id = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new SkillMapper(), dogId, trickId));
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }*/
        String query = "SELECT * FROM skill WHERE dog_id = ? AND trick_id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, new SkillMapper(), dogId, trickId));
    }
}
