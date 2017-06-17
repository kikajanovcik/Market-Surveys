package io.kikajanovcik.maketsurveys.services;

import io.kikajanovcik.maketsurveys.classes.SurveyRequest;
import io.kikajanovcik.maketsurveys.classes.Subscription.Frequency;
import io.kikajanovcik.maketsurveys.classes.SurveySubscription;
import io.kikajanovcik.maketsurveys.entities.Requester;
import io.kikajanovcik.maketsurveys.entities.Survey;
import io.kikajanovcik.maketsurveys.repositories.RequesterRepository;
import io.kikajanovcik.maketsurveys.repositories.SurveyRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SurveyService {

    @Autowired SurveyRepository surveys;
    @Autowired RequesterRepository requesters;

    private final ThreadPoolTaskScheduler taskExecutor;

    private static final Logger logger = LogManager.getLogger(SurveyService.class);

    public static final CronTrigger DAILY = new CronTrigger("0 0 12 1/1 * ?");
    public static final CronTrigger WEEKLY = new CronTrigger("0 0 12 ? * MON");
    public static final CronTrigger MONTHLY = new CronTrigger("0 0 12 1 1/1 ?");

    public SurveyService() {
        taskExecutor = new ThreadPoolTaskScheduler();
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setErrorHandler(t -> logger.error("Unknown error occurred while executing task", t));
        taskExecutor.initialize();
    }

    public void subscribe(SurveyRequest surveyRequest) {

        Requester requester = requesters.findOne(surveyRequest.getRequester().getId());
        surveyRequest.setRequester(requester);
        logger.info("SurveyRequest received from " + requester.getName());
        logger.info("Channels to receive subscription " + requester.getChannels());

        surveyRequest.setSurveyService(this);
        SurveySubscription surveySubscription = new SurveySubscription(surveyRequest);
        Frequency frequency = surveyRequest.getSubscription().getFrequency();
        taskExecutor.schedule(surveySubscription, getFrequencyTrigger(frequency));

        logger.info("Scheduling successful to be done " + frequency);
    }

    public List<Survey> getSurveys(Map<String, Object> queries) {

        String subject = (String) queries.get("subject");
        String country = (String) queries.get("country");
        Integer minAge = (Integer) queries.get("minAge");
        Integer maxAge = (Integer) queries.get("maxAge");

        return surveys.findAll().stream()
                .filter(s ->
                        (isNotSelected(subject) || s.getSubject().equalsIgnoreCase(subject)) &&
                        (isNotSelected(country) || s.getCountry().equalsIgnoreCase(country)) &&
                        (isNotSelected(minAge) || s.getTargetAge()[0] >= minAge) &&
                        (isNotSelected(maxAge) || s.getTargetAge()[1] <= maxAge))
                .collect(Collectors.toList());
    }

    private CronTrigger getFrequencyTrigger(Frequency frequency) {
        switch (frequency) {
            case DAILY: return DAILY;
            case WEEKLY: return WEEKLY;
            case MONTHLY: return MONTHLY;
            default: throw new IllegalArgumentException("Unexpected frequency " + frequency);
        }
    }

    private boolean isNotSelected(Object object){
        return object == null;
    }
}
