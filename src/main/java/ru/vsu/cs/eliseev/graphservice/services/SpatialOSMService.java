package ru.vsu.cs.eliseev.graphservice.services;

import ru.vsu.cs.eliseev.graphservice.model.entities.SpatialOSM;

/**
 * Сервис для работы с пространственными сущностями OSM в MongoDB.
 */
public interface SpatialOSMService {

    /**
     * Находит пространственную сущность по её идентификатору.
     *
     * @param id идентификатор объекта OSM
     * @return найденная пространственная сущность
     * @throws IllegalArgumentException если объект не найден
     */
    SpatialOSM getById(String id);
}
