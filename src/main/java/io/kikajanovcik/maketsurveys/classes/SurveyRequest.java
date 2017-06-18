package io.kikajanovcik.maketsurveys.classes;

import io.kikajanovcik.maketsurveys.entities.Provider;
import io.kikajanovcik.maketsurveys.entities.Requester;
import io.kikajanovcik.maketsurveys.entities.Survey;


public class SurveyRequest {

    private Requester requester;
    private Provider provider;
    private Survey survey;
    private Subscription subscription;

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

    public Survey getSurvey() {
        return survey;
    }

    public boolean isValid() {
        return getRequester() != null && getProvider() != null && getSubscription() != null && getSurvey() != null;
    }
}