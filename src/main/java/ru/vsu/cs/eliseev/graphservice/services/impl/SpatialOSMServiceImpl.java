package ru.vsu.cs.eliseev.graphservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vsu.cs.eliseev.graphservice.model.entities.SpatialOSM;
import ru.vsu.cs.eliseev.graphservice.repositories.SpatialOSMRepository;
import ru.vsu.cs.eliseev.graphservice.services.SpatialOSMService;

/**
 * Реализация сервиса {@link SpatialOSMService}
 * для работы с пространственными объектами в MongoDB.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SpatialOSMServiceImpl implements SpatialOSMService {

    private final SpatialOSMRepository spatialOSMRepository;

    @Override
    public SpatialOSM getById(String id) {
        log.debug("Searching for SpatialOSM with id={}", id);
        return spatialOSMRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("SpatialOSM with id={} not found", id);
                    return new IllegalArgumentException("SpatialOSM with id=" + id + " not found");
                });
    }
}
