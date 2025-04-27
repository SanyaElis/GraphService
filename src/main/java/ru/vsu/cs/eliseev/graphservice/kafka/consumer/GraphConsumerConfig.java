package ru.vsu.cs.eliseev.graphservice.kafka.consumer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.vsu.cs.eliseev.graphservice.model.dto.WayGraphRequestDTO;
import ru.vsu.cs.eliseev.graphservice.properties.KafkaConsumerProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@RequiredArgsConstructor
public class GraphConsumerConfig {

    private final KafkaConsumerProperties properties;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, WayGraphRequestDTO>> kafkaListenerContainerFactoryWayRequest() {
        ConcurrentKafkaListenerContainerFactory<String, WayGraphRequestDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(properties.getConcurrency());
        factory.getContainerProperties().setPollTimeout(1500);
        return factory;
    }

    private ConsumerFactory<String, WayGraphRequestDTO> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(WayGraphRequestDTO.class, false)
        );
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, properties.getEnableAutoCommit());
        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, properties.getAutoCommitInterval());
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, properties.getSession().getTimeout());
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, properties.getAutoOffsetReset());
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, properties.getGroupId());

        if (properties.getProperties() != null) {
            propsMap.putAll(properties.getProperties());
        }
        return propsMap;
    }
}
