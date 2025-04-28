package ru.vsu.cs.eliseev.graphservice.services;

import ru.vsu.cs.eliseev.graphservice.model.entities.SpatialOSM;

import java.util.List;

/**
 * Сервис для выполнения пространственных запросов к MongoDB.
 */
public interface SpatialMongoQueryService {

    /**
     * Находит кандидатов на связь по пространственному запросу вокруг заданного Way объекта.
     *
     * @param way объект Way (LineString или Polygon).
     * @return список SpatialOSM объектов-кандидатов.
     */
    List<SpatialOSM> findCandidatesNearWay(SpatialOSM way);
}
