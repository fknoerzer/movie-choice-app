package com.github.fknoerzer.backend.repository;

import com.github.fknoerzer.backend.model.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaRepo extends MongoRepository<Criteria, String> {
}
