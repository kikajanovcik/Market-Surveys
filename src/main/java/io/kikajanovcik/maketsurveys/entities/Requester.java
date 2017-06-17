package io.kikajanovcik.maketsurveys.entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Requester {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ElementCollection
    private Map<String, String> channels;
    private String name;

    public Requester(String name, Map<String, String> channels) {
        this.name = name;
        this.channels = channels;
    }

    public Requester(Long id) {
        this.id = id;
    }

    public Requester() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getChannels() {
        return channels;
    }
}
