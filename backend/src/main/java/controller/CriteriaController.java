package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CriteriaService;


import model.Criteria;

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
