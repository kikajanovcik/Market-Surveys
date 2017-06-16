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
    public enum FREQUENCY { DAILY, WEEKLY, MONTHLY };
    private FREQUENCY subscriptionFrequency;
    private String[] subscriptionChannels;

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

    public FREQUENCY getSubscriptionFrequency() {
        return subscriptionFrequency;
    }

    public void setSubscriptionFrequency(FREQUENCY subscriptionFrequency) {
        this.subscriptionFrequency = subscriptionFrequency;
    }

    public String[] getSubscriptionChannels() {
        return subscriptionChannels;
    }

    public void setSubscriptionChannels(String[] subscriptionChannels) {
        this.subscriptionChannels = subscriptionChannels;
    }
}
