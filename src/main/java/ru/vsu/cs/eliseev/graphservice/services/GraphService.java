package ru.vsu.cs.eliseev.graphservice.services;

import ru.vsu.cs.eliseev.graphservice.model.dto.WayGraphRequestDTO;

/**
 * Сервис для обработки Way объектов и построения графа.
 */
public interface GraphService {

    /**
     * Обработка запроса на построение графа для указанного Way объекта.
     *
     * @param request объект запроса с ID и типом OSM-объекта.
     */
    void processWayRequest(WayGraphRequestDTO request);
}
