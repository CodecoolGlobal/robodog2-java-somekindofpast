package com.codecool.robodog2.service;

import com.codecool.robodog2.DTO.PedigreeDTO;
import com.codecool.robodog2.dao.PedigreeDAO;
import com.codecool.robodog2.model.Pedigree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedigreeService {

    private PedigreeDAO pedigreeRepository;

    @Autowired
    public PedigreeService(PedigreeDAO pedigreeRepository) {
        this.pedigreeRepository = pedigreeRepository;
    }

    public void addPedigree(PedigreeDTO pedigree) {
        pedigreeRepository.addPedigree(new Pedigree(pedigree.getMomId(), pedigree.getDadId(), pedigree.getPuppyId()));
    }

    public List<Pedigree> listAllPedigrees() {
        return pedigreeRepository.listPedigrees();
    }

    public Pedigree getPedigreeById(long id) {
        return pedigreeRepository.getPedigree(id);
    }

    public void updatePedigree(long id, PedigreeDTO pedigree) {
        pedigreeRepository.updatePedigree(new Pedigree(pedigree.getMomId(), pedigree.getDadId(), pedigree.getPuppyId()), id);
    }

    public void deletePedigree(long id) {
        pedigreeRepository.deletePedigree(id);
    }
}
