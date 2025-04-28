package ru.vsu.cs.eliseev.graphservice.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.vsu.cs.eliseev.graphservice.model.dto.WayGraphRequestDTO;
import ru.vsu.cs.eliseev.graphservice.services.GraphService;

/**
 * Kafka consumer для обработки сообщений о новых объектах OSM,
 * для которых необходимо построить граф на основе пространственных отношений.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GraphConsumer {

    private final GraphService graphService;

    /**
     * Принимает сообщение из Kafka и инициирует процесс построения графа для Way объекта.
     *
     * @param request DTO с id объекта и его типом.
     */
    @KafkaListener(
            topics = "${spring.kafka.consumer.graph-topic}",
            containerFactory = "kafkaListenerContainerFactoryWayRequest"
    )
    public void receive(@Payload WayGraphRequestDTO request) {
        log.info("Received WayGraphRequestDTO: id={}, osmType={}", request.getId(), request.getOsmType());

        try {
            graphService.processWayRequest(request);
            log.info("Successfully processed WayGraphRequestDTO with id={}", request.getId());
        } catch (IllegalArgumentException e) {
            log.warn("Invalid data in WayGraphRequestDTO: id={}, reason={}", request.getId(), e.getMessage());
        } catch (Exception e) {
            log.error("Error while processing WayGraphRequestDTO: id={}, error={}", request.getId(), e.getMessage(), e);
        }
    }
}
