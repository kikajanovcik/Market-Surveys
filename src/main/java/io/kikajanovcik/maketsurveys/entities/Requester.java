package io.kikajanovcik.maketsurveys.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Requester {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String name;

    public Requester(String name) {
        this.name = name;
    }

    public Requester() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
