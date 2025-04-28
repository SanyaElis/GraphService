package ru.vsu.cs.eliseev.graphservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.eliseev.graphservice.model.enums.OSMType;

/**
 * Kafka consumer для обработки сообщений о новых объектах OSM,
 * для которых необходимо построить граф на основе пространственных отношений.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WayGraphRequestDTO {
    private String id;
    private OSMType osmType;
}
