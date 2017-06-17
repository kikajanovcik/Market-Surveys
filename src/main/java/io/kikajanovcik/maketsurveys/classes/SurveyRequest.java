package io.kikajanovcik.maketsurveys.classes;

import io.kikajanovcik.maketsurveys.entities.Requester;
import io.kikajanovcik.maketsurveys.entities.Survey;
import io.kikajanovcik.maketsurveys.services.SurveyService;

import java.util.List;
import java.util.Map;

public class SurveyRequest {

    private SurveyService surveyService;
    private Requester requester;
    private Subscription subscription;
    private Map<String, Object> queries;
    private List<Survey> surveys;

    public SurveyRequest(Requester requester, Subscription subscription, Map<String, Object> queries) {
        this.requester = requester;
        this.subscription = subscription;
        this.queries = queries;
    }

    public SurveyRequest() {}

    public Requester getRequester() {
        return requester;
    }

    public void setRequester(Requester requester) {
        this.requester = requester;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public Map<String, Object> getQueries() {
        return queries;
    }

    public List<Survey> getSurveys() {
        return surveyService.getSurveys(getQueries());
    }

    public SurveyService getSurveyService() {
        return surveyService;
    }

    public void setSurveyService(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public boolean isInvalid() {
        return getRequester() == null || getSubscription() == null || getQueries() == null;
    }
}