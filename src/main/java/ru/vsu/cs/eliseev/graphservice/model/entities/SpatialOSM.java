package ru.vsu.cs.eliseev.graphservice.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.vsu.cs.eliseev.graphservice.model.enums.OSMType;

import java.util.Map;

/**
 * Документ для хранения пространственного представления объекта OSM
 * в MongoDB с пространственным индексированием.
 */
@Document("spatial_osm_test")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpatialOSM {

    @Id
    private String osmId;
    private OSMType type;
    private Map<String, String> tags;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJson<?> geometry;
}
