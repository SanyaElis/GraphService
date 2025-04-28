package ru.vsu.cs.eliseev.graphservice.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import ru.vsu.cs.eliseev.graphservice.model.enums.OSMType;

import java.util.List;

/**
 * Узел графа для представления пространственного объекта из OSM.
 */
@Node("SpatialEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpatialEntityNode {

    @Id
    @GeneratedValue
    private Long id;

    private String osmId;

    private OSMType type; // NODE, WAY, RELATION
    private String name; // можно из тега name брать

    /**
     * Связи этого узла с другими пространственными сущностями.
     */
    @Relationship(type = "SPATIAL_RELATION")
    private List<SpatialRelation> relations;
}
