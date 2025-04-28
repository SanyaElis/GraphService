package ru.vsu.cs.eliseev.graphservice.utils;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.util.List;

/**
 * Утилита для конвертации GeoJSON объектов MongoDB в JTS геометрию.
 */
public class GeoJsonToJtsConverter {

    private static final GeometryFactory geometryFactory = new GeometryFactory();

    /**
     * Конвертирует GeoJson объект в JTS Geometry.
     *
     * @param geometry GeoJson<?> из MongoDB
     * @return JTS Geometry
     */
    public static Geometry convertToJts(GeoJson<?> geometry) {
        String type = geometry.getType();

        return switch (type) {
            case "LineString" -> toJtsLineString((GeoJsonLineString) geometry);
            case "Polygon" -> toJtsPolygon((GeoJsonPolygon) geometry);
            default -> throw new UnsupportedOperationException("Unsupported GeoJson type: " + type);
        };
    }

    private static LineString toJtsLineString(GeoJsonLineString geoJsonLineString) {
        List<Point> points = geoJsonLineString.getCoordinates();
        Coordinate[] coords = points.stream()
                .map(p -> new Coordinate(p.getX(), p.getY()))
                .toArray(Coordinate[]::new);
        return geometryFactory.createLineString(coords);
    }

    private static Polygon toJtsPolygon(GeoJsonPolygon geoJsonPolygon) {
        List<Point> points = geoJsonPolygon.getCoordinates().get(0).getCoordinates();
        Coordinate[] coords = points.stream()
                .map(p -> new Coordinate(p.getX(), p.getY()))
                .toArray(Coordinate[]::new);
        LinearRing shell = geometryFactory.createLinearRing(coords);
        return geometryFactory.createPolygon(shell);
    }
}