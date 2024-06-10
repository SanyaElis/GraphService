package ru.vsu.cs.eliseev.graphservice.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Node("OSMObject")
public class OSMObject {
    @Id
    @GeneratedValue
    private Long id;

    private String type;
    private String name;
    private Double latitude;
    private Double longitude;

    @Relationship(type = "NEAR")
    private Set<OSMObject> nearObjects = new HashSet<>();

    @Relationship(type = "ADJACENT")
    private Set<OSMObject> adjacentObjects = new HashSet<>();

    @Relationship(type = "CROSS")
    private Set<OSMObject> crossObjects = new HashSet<>();

    @Relationship(type = "CONTAIN")
    private Set<OSMObject> containedObjects = new HashSet<>();

    public OSMObject(String type, String name, Double latitude, Double longitude) {
        this.type = type;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public OSMObject() {
    }

    public void addNearObject(OSMObject object) {
        nearObjects.add(object);
    }

    public void addAdjacentObject(OSMObject object) {
        adjacentObjects.add(object);
    }

    public void addCrossObject(OSMObject object) {
        crossObjects.add(object);
    }

    public void addContainedObject(OSMObject object) {
        containedObjects.add(object);
    }
}