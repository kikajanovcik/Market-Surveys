package io.kikajanovcik.maketsurveys.services;

import io.kikajanovcik.maketsurveys.classes.Subscription;
import io.kikajanovcik.maketsurveys.classes.SurveyRequest;
import io.kikajanovcik.maketsurveys.classes.SurveySubscriptionTask;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;


@Service
public class SurveySubscriptionService {

    @Autowired SurveyService surveyService;
    private static final Logger logger = LogManager.getLogger(SurveyService.class);

    private final ThreadPoolTaskScheduler taskExecutor;

    private static final CronTrigger DAILY = new CronTrigger("0 0 12 1/1 * ?");
    private static final CronTrigger WEEKLY = new CronTrigger("0 0 12 ? * MON");
    private static final CronTrigger MONTHLY = new CronTrigger("0 0 12 1 1/1 ?");

    public SurveySubscriptionService() {
        taskExecutor = new ThreadPoolTaskScheduler();
        taskExecutor.setErrorHandler(t -> logger.error("Unknown error occurred while executing task", t));
        taskExecutor.initialize();
    }

    public void subscribe(SurveyRequest surveyRequest) {

        logger.info("SurveyRequest received from " + surveyRequest.getRequester().getName());

        SurveySubscriptionTask surveySubscription = new SurveySubscriptionTask(surveyRequest, surveyService);
        Subscription.Frequency frequency = surveyRequest.getSubscription().getFrequency();

        // Execute task once at the act of subscribing, then periodically
        taskExecutor.execute(surveySubscription);
        taskExecutor.schedule(surveySubscription, getFrequencyTrigger(frequency));

        logger.info("Scheduling successful to be done " + frequency);
    }

    private CronTrigger getFrequencyTrigger(Subscription.Frequency frequency) {
        switch (frequency) {
            case DAILY: return DAILY;
            case WEEKLY: return WEEKLY;
            case MONTHLY: return MONTHLY;
            default: throw new IllegalArgumentException("Unexpected frequency " + frequency);
        }
    }
}
