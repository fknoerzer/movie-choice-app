package com.github.fknoerzer.backend.controller;

import com.github.fknoerzer.backend.service.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.github.fknoerzer.backend.model.Criteria;

@RestController
@RequestMapping("api/criteria")
public class CriteriaController {

    private final CriteriaService criteriaService;

    @Autowired
    public CriteriaController(CriteriaService criteriaService) {
        this.criteriaService = criteriaService;
    }

    @GetMapping("{id}")
    public Criteria getCriteriaById(@PathVariable String id) {
       return criteriaService.getById(id);
    }

    @PostMapping
    public Criteria addNewCriteria(@RequestBody Criteria newCriteria) {
        return criteriaService.addNewCriteria(newCriteria);
    }

}
