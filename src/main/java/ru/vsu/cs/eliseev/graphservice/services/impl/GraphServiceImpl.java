package ru.vsu.cs.eliseev.graphservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vsu.cs.eliseev.graphservice.model.dto.WayGraphRequestDTO;
import ru.vsu.cs.eliseev.graphservice.model.entities.SpatialOSM;
import ru.vsu.cs.eliseev.graphservice.services.GraphService;
import ru.vsu.cs.eliseev.graphservice.services.SpatialMongoQueryService;
import ru.vsu.cs.eliseev.graphservice.services.SpatialOSMService;

import java.util.List;

/**
 * Реализация сервиса {@link  GraphService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GraphServiceImpl implements GraphService {

    private final SpatialMongoQueryService spatialMongoQueryService;
    private final SpatialOSMService spatialOSMService;

    @Override
    public void processWayRequest(WayGraphRequestDTO request) {
        log.info("Starting graph construction for id={} of type={}", request.getId(), request.getOsmType());

        SpatialOSM way = spatialOSMService.getById(request.getId());
        // Здесь пока будет заглушка
        // Потом сюда пойдет реальная логика построения графа
        List<SpatialOSM> result = spatialMongoQueryService.findCandidatesNearWay(way);

        log.info("Graph construction completed for id={}", request.getId());
    }
}
