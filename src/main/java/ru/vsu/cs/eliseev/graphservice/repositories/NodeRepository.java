package ru.vsu.cs.eliseev.graphservice.repositories;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.eliseev.graphservice.model.entities.Node;

import java.util.List;

@Repository
public interface NodeRepository extends MongoRepository<Node, String> {
    List<Node> findByLocationNear(Point location, Distance maxDistance);

}
