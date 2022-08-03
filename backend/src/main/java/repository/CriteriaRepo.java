package repository;

import model.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaRepo extends MongoRepository<Criteria, String> {
}
