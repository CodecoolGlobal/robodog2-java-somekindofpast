package com.codecool.robodog2.dao.repository;

import com.codecool.robodog2.dao.TrickDAO;
import com.codecool.robodog2.dao.mapper.TrickMapper;
import com.codecool.robodog2.model.Trick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrickJdbcDAO implements TrickDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TrickJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addTrick(Trick trick) {
        String query = "INSERT INTO trick (name) VALUES (?)";
        jdbcTemplate.update(query, trick.getName());
    }

    @Override
    public List<Trick> listTricks() {
        String query = "SELECT name FROM trick";
        return jdbcTemplate.query(query, new TrickMapper());
    }

    @Override
    public Trick getTrick(long id) {
        String query = "SELECT name FROM trick WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new TrickMapper(), id);
    }

    @Override
    public void updateTrick(Trick trick, long id) {
        String query = "UPDATE trick SET name = ? WHERE id = ?";
        jdbcTemplate.update(query, trick.getName(), id);
    }

    @Override
    public void deleteTrick(long id) {
        String query = "DELETE FROM trick WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
