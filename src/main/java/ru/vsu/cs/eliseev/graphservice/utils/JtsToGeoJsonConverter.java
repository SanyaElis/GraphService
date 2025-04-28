package ru.vsu.cs.eliseev.graphservice.utils;

import org.locationtech.jts.geom.*;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Утилита для конвертации JTS-геометрии обратно в GeoJsonPolygon.
 */
public class JtsToGeoJsonConverter {

    public static GeoJsonPolygon convertPolygonToGeoJson(Polygon polygon) {
        Coordinate[] coords = polygon.getExteriorRing().getCoordinates();

        List<Point> points =
                java.util.Arrays.stream(coords)
                        .map(c -> new Point(c.getX(), c.getY()))
                        .collect(Collectors.toList());

        return new GeoJsonPolygon(points);
    }
}
