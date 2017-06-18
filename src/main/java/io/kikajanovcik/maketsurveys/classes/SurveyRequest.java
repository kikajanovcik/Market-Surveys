package io.kikajanovcik.maketsurveys.classes;

import io.kikajanovcik.maketsurveys.entities.Provider;
import io.kikajanovcik.maketsurveys.entities.Requester;

import java.util.Map;

public class SurveyRequest {

    private Requester requester;
    private Provider provider;
    private Subscription subscription;
    private Map<String, Object> queries;

    public Requester getRequester() {
        return requester;
    }

    public void setRequester(Requester requester) {
        this.requester = requester;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public Map<String, Object> getQueries() {
        return queries;
    }

    public boolean isValid() {
        return getRequester() != null && getProvider() != null && getSubscription() != null && getQueries() != null;
    }
}