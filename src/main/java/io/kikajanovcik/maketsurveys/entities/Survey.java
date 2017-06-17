package io.kikajanovcik.maketsurveys.entities;

import javax.persistence.*;

@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    private String subject;
    private String country;
    private Integer[] targetAge = new Integer[2];

    public Survey(Provider provider, String subject, String country, Integer[] targetAge) {
        this.provider = provider;
        this.subject = subject;
        this.country = country;
        this.targetAge = targetAge;
    }

    public Survey() {}

    public Long getId() {
        return id;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getSubject() {
        return subject;
    }

    public String getCountry() {
        return country;
    }

    public Integer[] getTargetAge() {
        return targetAge;
    }
}
