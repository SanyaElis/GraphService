package ru.vsu.cs.eliseev.graphservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.vsu.cs.eliseev.graphservice.model.entities.SpatialOSM;

public interface SpatialOSMRepository extends MongoRepository<SpatialOSM, String> {

}
