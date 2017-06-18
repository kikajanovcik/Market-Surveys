package io.kikajanovcik.maketsurveys.entities;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.persistence.*;
import java.util.List;

@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToMany(mappedBy="provider", fetch=FetchType.EAGER)
    private List<Survey> surveys;

    private String name;

    public Provider(String name) {
        this.name = name;
    }

    public Provider() {}

    public Long getId() {
        return id;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode();
    }
}
