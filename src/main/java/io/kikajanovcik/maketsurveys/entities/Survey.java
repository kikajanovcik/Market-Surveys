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
    private int[] targetAge = new int[2];

    public Survey(Provider provider, String subject, String country) {
        this.provider = provider;
        this.subject = subject;
        this.country = country;
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

    public int[] getTargetAge() {
        return targetAge;
    }
}
