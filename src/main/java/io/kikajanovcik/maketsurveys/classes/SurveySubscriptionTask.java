package io.kikajanovcik.maketsurveys.classes;

import io.kikajanovcik.maketsurveys.entities.Survey;
import io.kikajanovcik.maketsurveys.services.SurveyService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class SurveySubscriptionTask implements Runnable {

    private static final Logger logger = LogManager.getLogger(SurveyRequest.class);

    private SurveyRequest surveyRequest;
    private SurveyService surveyService;

    public SurveySubscriptionTask(SurveyRequest surveyRequest, SurveyService surveyService) {
        this.surveyRequest = surveyRequest;
        this.surveyService = surveyService;
    }

    public SurveySubscriptionTask() {}

    @Override
    public void run() {

        logger.info("Searching for surveys of " + surveyRequest.getQueries() + " with provider: " + surveyRequest.getProvider().getName());

        try {
            List<Survey> surveys = surveyService.getSurveys(surveyRequest.getProvider(), surveyRequest.getQueries());
            logger.info("Found " + surveys.size() + " surveys");

            // send surveys only to channels we support
            List<String> channels = surveyRequest.getSubscription().getChannels();
            for (Map.Entry<String, String> channel : surveyRequest.getRequester().getChannels().entrySet()) {
                if (channels.contains(channel.getKey())) {
                    sendRespose(channel.getValue());
                }
            }
        } catch (Exception e) {
            logger.info("An error occurred, the surveys could not be sent");
        } finally {
            logger.info("Task done");
        }
    }

    private void sendRespose(String channel) {
        logger.info("Sending surveys to " + channel);
    }
}
