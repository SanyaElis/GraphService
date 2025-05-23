package ru.vsu.cs.eliseev.graphservice.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Ways")
@TypeAlias("Way")
@Getter
@Setter
public class Way extends ElementOnMap{

    private List<String> nodes;

    public Way(String id) {
        super(id);
        this.nodes = new ArrayList<>();
    }

    public void addNode(String refNode) {
        if (refNode != null)
            nodes.add(refNode);
    }

}
