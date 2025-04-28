package ru.vsu.cs.eliseev.graphservice.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ru.vsu.cs.eliseev.graphservice.model.entities.SpatialEntityNode;

/**
 * Репозиторий для управления пространственными узлами в графе.
 */
public interface SpatialEntityRepository extends Neo4jRepository<SpatialEntityNode, Long> {
}
