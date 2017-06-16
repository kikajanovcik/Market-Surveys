package io.kikajanovcik.maketsurveys.entities;

import java.util.List;

public class Request implements Runnable {

    private Requester requester;
    private Provider provider;
    private Object query;
    private List<Object> response;

    @Override
    public void run() {

        System.out.println("i'm running");
    }

    public Request(Requester requester, Provider provider) {
        this.requester = requester;
        this.provider = provider;
    }

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

    public Object getQuery() {
        return query;
    }

    public void setQuery(Object query) {
        this.query = query;
    }

    public List<Object> getResponse() {
        return response;
    }

    public void setResponse(List<Object> response) {
        this.response = response;
    }
}