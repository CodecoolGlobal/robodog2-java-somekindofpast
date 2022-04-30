package com.codecool.robodog2.controller;

import com.codecool.robodog2.DTO.TrickDTO;
import com.codecool.robodog2.model.Trick;
import com.codecool.robodog2.service.TrickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trick")
public class TrickController {
    private TrickService trickService;

    @Autowired
    public TrickController(TrickService trickService) {
        this.trickService = trickService;
    }

    @GetMapping
    public List<Trick> getAllTricks() {
        return trickService.listAllTricks();
    }

    @GetMapping("/{id}")
    public Trick getTrickById(@PathVariable("id") long id) {
        return trickService.getTrickById(id);
    }

    @PostMapping("/new")
    public void addTrick(@RequestBody TrickDTO trick) {
        trickService.addTrick(trick);
    }

    @PutMapping("/update/{id}")
    public void updateTrick(@PathVariable("id") long id, @RequestBody TrickDTO trick) {
        trickService.updateTrick(id, trick);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTrick(@PathVariable("id") long id) {
        trickService.deleteTrick(id);
    }
}
