package ru.vsu.cs.eliseev.graphservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.eliseev.graphservice.models.Way;

import java.util.List;

@Repository
public interface WayRepository extends MongoRepository<Way, String> {
    List<Way> findByNodesContaining(String refNode);
}
