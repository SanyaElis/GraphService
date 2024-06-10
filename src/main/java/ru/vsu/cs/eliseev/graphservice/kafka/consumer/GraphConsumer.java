package ru.vsu.cs.eliseev.graphservice.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GraphConsumer {


    @KafkaListener(topics = "${kafka.topic.name.for-input-topic}", containerFactory = "kafkaListenerContainerFactory", concurrency = "${kafka.topic.partitions.for-input-topic}")
    public void receive(String articleJson) {
        System.out.println(articleJson);
        /*try {
        } catch (Exception e) {
            System.out.println("Error in kafka listen" + e.getMessage());
        }*/
    }
}
