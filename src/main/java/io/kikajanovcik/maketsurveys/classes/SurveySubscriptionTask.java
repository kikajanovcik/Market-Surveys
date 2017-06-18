package io.kikajanovcik.maketsurveys.classes;

import io.kikajanovcik.maketsurveys.entities.Survey;
import io.kikajanovcik.maketsurveys.services.SurveyService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;


public class SurveySubscriptionTask implements Runnable {

    private static final Logger logger = LogManager.getLogger(SurveyRequest.class);

    private final SurveyRequest surveyRequest;
    private final SurveyService surveyService;

    public SurveySubscriptionTask(SurveyRequest surveyRequest, SurveyService surveyService) {
        this.surveyRequest = surveyRequest;
        this.surveyService = surveyService;
    }

    @Override
    public void run() {

        logger.info("Searching for surveys of " + surveyRequest.getSurvey());

        try {
            List<Survey> surveys = surveyService.getSurveys(surveyRequest.getProvider(), surveyRequest.getSurvey());
            logSurveys(surveys);
            sendResponsesToAvailableChannels();

        } catch (Exception e) {
            logger.info("An error occurred, the surveys could not be sent");
        } finally {
            logger.info("Task done");
        }
    }

    private void logSurveys(List<Survey> surveys) {
        logger.info("Found " + surveys.size() + " surveys");
        // Display found surveys to show they are correct
        for (Survey survey : surveys) {
            logger.info(survey);
        }
    }

    private void sendResponsesToAvailableChannels() {
        List<String> channels = surveyRequest.getSubscription().getChannels();
        Map<String, String> availableChannels = surveyRequest.getRequester().getChannels();
        for (String channel : channels) {
            if (availableChannels.containsKey(channel)) {
                sendResponse(availableChannels.get(channel));
            }
        }
    }

    private void sendResponse(String channel) {
        logger.info("Sending surveys to " + channel);
    }
}
