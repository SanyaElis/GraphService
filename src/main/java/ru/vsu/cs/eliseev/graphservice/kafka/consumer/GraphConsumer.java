package ru.vsu.cs.eliseev.graphservice.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.vsu.cs.eliseev.graphservice.model.dto.WayGraphRequestDTO;

@Slf4j
@Component
public class GraphConsumer {

    @KafkaListener(
            topics = "${spring.kafka.consumer.graph-topic}",
            containerFactory = "kafkaListenerContainerFactoryWayRequest"
    )
    public void receive(WayGraphRequestDTO request) {
        log.info("Received message: {} with type: {}", request.getId(), request.getOsmType());
    }
}
