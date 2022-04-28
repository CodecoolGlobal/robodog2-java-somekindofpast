package com.codecool.robodog2.service;

import com.codecool.robodog2.DTO.TrickDTO;
import com.codecool.robodog2.dao.TrickDAO;
import com.codecool.robodog2.model.Trick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrickService {
    private TrickDAO trickRepository;

    @Autowired
    public TrickService(TrickDAO trickRepository) {
        this.trickRepository = trickRepository;
    }

    public void addTrick(TrickDTO trick) {
        trickRepository.addTrick(new Trick(trick.getName()));
    }

    public List<Trick> listAllTricks() {
        return trickRepository.listTricks();
    }

    public Trick getTrickById(long id) {
        return trickRepository.getTrick(id);
    }

    public void updateTrick(long id, TrickDTO trick) {
        trickRepository.updateTrick(new Trick(trick.getName()), id);
    }

    public void deleteTrick(long id) {
        trickRepository.deleteTrick(id);
    }
}
