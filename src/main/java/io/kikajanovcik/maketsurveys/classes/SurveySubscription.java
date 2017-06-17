package io.kikajanovcik.maketsurveys.classes;

import io.kikajanovcik.maketsurveys.entities.Survey;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class SurveySubscription implements Runnable {

    private SurveyRequest surveyRequest;
    private static final Logger logger = LogManager.getLogger(SurveyRequest.class);

    @Override
    public void run() {
        logger.info("Searching for surveys of " + surveyRequest.getQueries());

        try {
            List<Survey> surveys = surveyRequest.getSurveys();
            logger.info("Found " + surveys.size() + " surveys");

            for (Map.Entry<String, String> channel : surveyRequest.getRequester().getChannels().entrySet()) {
                sendRespose(channel.getValue());
            }
        } catch (Exception e) {
            logger.info("No surveys found to be sent");
        } finally {
            logger.info("Task done");
        }
    }

    public SurveySubscription(SurveyRequest surveyRequest) {
        this.surveyRequest = surveyRequest;
    }

    public SurveySubscription() {}

    public void sendRespose(String channel) {
        logger.info("Sending surveys to " + channel);
    }
}
