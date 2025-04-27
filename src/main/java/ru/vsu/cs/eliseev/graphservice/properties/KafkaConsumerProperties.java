package ru.vsu.cs.eliseev.graphservice.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.kafka.consumer")
public class KafkaConsumerProperties {

    private Integer concurrency;
    private String groupId;
    private String osmTopic;
    private SessionProperties session;
    private String bootstrapServers;
    private String autoOffsetReset;
    private Boolean enableAutoCommit;
    private Integer autoCommitInterval;
    private Map<String, Object> properties;

    @Getter
    @Setter
    public static class SessionProperties {
        private Integer timeout;
    }
}
