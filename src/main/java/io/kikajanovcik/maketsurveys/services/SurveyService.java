package io.kikajanovcik.maketsurveys.services;

import io.kikajanovcik.maketsurveys.classes.Request;
import io.kikajanovcik.maketsurveys.classes.Subscription.FREQUENCY;
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

    private final ThreadPoolTaskScheduler taskExecutor;

    private static final Logger log = LogManager.getLogger("[Logger]");
    private static final Logger schedulingLogger = LogManager.getLogger(log.getName() + ".[Scheduling]");

    public static final CronTrigger DAILY = new CronTrigger("0 0 12 1/1 * ?");
    public static final CronTrigger WEEKLY = new CronTrigger("0 0 12 ? * MON");
    public static final CronTrigger MONTHLY = new CronTrigger("0 0 12 1 1/1 ?");

    public SurveyService() {
        taskExecutor = new ThreadPoolTaskScheduler();
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setErrorHandler(t -> schedulingLogger.error("Unknown error occurred while executing task.", t));
        taskExecutor.initialize();
    }

    public void subscribe(Request request) {

        schedulingLogger.info("Request received from " + request.getRequester().getName());

        schedulingLogger.info("Searching for surveys of " + request.getQueries());
        request.setResponse(getSurveys(request.getQueries()));

        schedulingLogger.info("Channels to receive subscription " + request.getSubscription().getChannels());

        //taskExecutor.scheduleWithFixedDelay(request, 30000);
        CronTrigger frequency = getFrequency(request.getSubscription().getFrequency());
        taskExecutor.schedule(request, frequency);
        schedulingLogger.info("Scheduling successful to be done " + request.getSubscription().getFrequency());
    }

    private List<Object> getSurveys(Map<String, Object> queries) {

        String subject = (String) queries.get("subject");
        String country = (String) queries.get("country");
        int minAge = (int) queries.get("minAge");
        int maxAge = (int) queries.get("maxAge");

        return surveys.findAll().stream()
                .filter(s ->
                        (isNotSelected(subject) || s.getSubject().equalsIgnoreCase(subject)) &&
                        (isNotSelected(country) || s.getCountry().equalsIgnoreCase(country)) &&
                        (isNotSelected(minAge) || s.getTargetAge()[0] >= minAge) &&
                        (isNotSelected(maxAge) || s.getTargetAge()[1] <= maxAge))
                .collect(Collectors.toList());
    }

    private void sendSurveys(List<Object> surveys) {
    }

    private CronTrigger getFrequency(FREQUENCY frequency) {
        switch (frequency) {
            case DAILY: return DAILY;
            case WEEKLY: return WEEKLY;
            default: return MONTHLY;
        }
    }

    private boolean isNotSelected(Object object){
        return object == null;
    }
}
