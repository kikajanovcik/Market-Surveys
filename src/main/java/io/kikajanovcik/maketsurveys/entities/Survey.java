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
    private Integer minAge;
    private Integer maxAge;

    public Survey(Provider provider, String subject, String country, Integer minAge, Integer maxAge) {
        this.provider = provider;
        this.subject = subject;
        this.country = country;
        this.minAge = minAge;
        this.maxAge = maxAge;
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

    public Integer getMinAge() {
        return minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    @Override
    public String toString() {
        return "subject: " + getSubject() + ", age: " +
                getMinAge() + " - " + getMaxAge() + " " + ", country: " + getCountry();
    }
}
