package io.kikajanovcik.maketsurveys.classes;

import io.kikajanovcik.maketsurveys.entities.Provider;
import io.kikajanovcik.maketsurveys.entities.Requester;

import java.util.List;
import java.util.Map;

public class Request implements Runnable {

    private Requester requester;
    private Provider provider;
    private Subscription subscription;
    private Map<String, Object> queries;
    private List<Object> response;

    @Override
    public void run() {
        System.out.println("i'm running");
    }

    public Request(Requester requester, Subscription subscription) {
        this.requester = requester;
    }

    public Request(Requester requester, Subscription subscription, Map<String, Object> queries) {
        this.requester = requester;
        this.subscription = subscription;
        this.queries = queries;
    }

    public Request() {
    }

    public Requester getRequester() {
        return requester;
    }

    public Provider getProvider() {
        return provider;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public Map<String, Object> getQueries() {
        return queries;
    }

    public List<Object> getResponse() {
        return response;
    }

    public void setResponse(List<Object> response) {
        this.response = response;
    }

    public boolean isInvalid() {
        return getRequester() == null || getSubscription() == null;
    }
}