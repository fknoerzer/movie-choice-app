package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CriteriaRepo;


import model.Criteria;

import java.util.NoSuchElementException;

@Service
public class CriteriaService {

    private final CriteriaRepo criteriaRepo;

    @Autowired
    public CriteriaService(CriteriaRepo criteriaRepo) {
        this.criteriaRepo = criteriaRepo;
    }

    public Criteria addNewCriteria(Criteria newCriteria) {
        return criteriaRepo.insert(newCriteria);
    }
    public Criteria getById(String id) {
        return criteriaRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Criteria with id: " + id + " was not found!"));
    }
}