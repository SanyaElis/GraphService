package ru.vsu.cs.eliseev.graphservice.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import ru.vsu.cs.eliseev.graphservice.model.enums.RelationType;

/**
 * Связь между двумя пространственными сущностями,
 * отражающая пространственное отношение на основе JTS вычислений.
 */
@RelationshipProperties
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpatialRelation {

    @Id
    @GeneratedValue
    private Long id;

    private RelationType relationType;

    @TargetNode
    private SpatialEntityNode target;
}
