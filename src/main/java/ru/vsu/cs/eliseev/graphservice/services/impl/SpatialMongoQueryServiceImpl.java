package ru.vsu.cs.eliseev.graphservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.vsu.cs.eliseev.graphservice.model.entities.SpatialOSM;
import ru.vsu.cs.eliseev.graphservice.services.SpatialMongoQueryService;
import ru.vsu.cs.eliseev.graphservice.utils.GeoJsonToJtsConverter;
import ru.vsu.cs.eliseev.graphservice.utils.JtsToGeoJsonConverter;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpatialMongoQueryServiceImpl implements SpatialMongoQueryService {

    private final MongoTemplate mongoTemplate;

    private static final double METERS_TO_DEGREES = 1.0 / 111320; // примерно: 1 градус ≈ 111.32 км

    private static final double SEARCH_RADIUS_METERS = 50; // 50 метров

    @Override
    public List<SpatialOSM> findCandidatesNearWay(SpatialOSM way) {
        log.info("Searching candidates within {} meters of way id={}", SEARCH_RADIUS_METERS, way.getOsmId());

        if (way.getGeometry() == null) {
            log.error("Way with id={} has no geometry!", way.getOsmId());
            throw new IllegalArgumentException("Way must have geometry.");
        }

        Geometry jtsGeometry;
        try {
            jtsGeometry = GeoJsonToJtsConverter.convertToJts(way.getGeometry());
        } catch (UnsupportedOperationException e) {
            log.error("Unsupported geometry type for way id={}: {}", way.getOsmId(), e.getMessage());
            throw e;
        }

        double bufferDistanceInDegrees = SEARCH_RADIUS_METERS * METERS_TO_DEGREES;
        Geometry bufferedGeometry = jtsGeometry.buffer(bufferDistanceInDegrees);

        // Проверка, что после buffer мы получили корректную область
        if (!Geometry.TYPENAME_POLYGON.equalsIgnoreCase(bufferedGeometry.getGeometryType()) &&
                !Geometry.TYPENAME_MULTIPOLYGON.equalsIgnoreCase(bufferedGeometry.getGeometryType())) {
            log.error("Buffering failed to produce a Polygon or MultiPolygon for way id={}", way.getOsmId());
            throw new IllegalStateException("Buffer must produce Polygon or MultiPolygon geometry.");
        }

        // Преобразование буфера в GeoJsonPolygon
        GeoJsonPolygon bufferGeoJson = JtsToGeoJsonConverter.convertPolygonToGeoJson((Polygon) bufferedGeometry);

        Query query = new Query();
        query.addCriteria(Criteria.where("geometry").within(bufferGeoJson));

        List<SpatialOSM> candidates = mongoTemplate.find(query, SpatialOSM.class);

        log.info("Found {} candidate objects near way id={}", candidates.size(), way.getOsmId());

        return candidates;
    }
}
