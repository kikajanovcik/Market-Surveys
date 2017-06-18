package io.kikajanovcik.maketsurveys.controllers;

import io.kikajanovcik.maketsurveys.classes.SurveyRequest;
import io.kikajanovcik.maketsurveys.entities.Provider;
import io.kikajanovcik.maketsurveys.entities.Requester;
import io.kikajanovcik.maketsurveys.services.ProviderService;
import io.kikajanovcik.maketsurveys.services.RequesterService;
import io.kikajanovcik.maketsurveys.services.SurveySubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/surveys")
public class SurveyController {

    @Autowired private SurveySubscriptionService surveySubsriptionService;
    @Autowired private RequesterService requesterService;
    @Autowired private ProviderService providerService;

    private static final String BAD_REQUEST_MESSAGE = "Process interrupted due to invalid or incorrect data";
    private static final String SUCCESS_MESSAGE = "You have been successfully subscribed";

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public ResponseEntity<Object> subscribe(@RequestBody SurveyRequest surveyRequest) {

        final HttpStatus status = fillRequest(surveyRequest);
        if (status != HttpStatus.OK) {
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE, status);
        }

        surveySubsriptionService.subscribe(surveyRequest);
        return new ResponseEntity<>(SUCCESS_MESSAGE, status);
    }

    /** Checks the request and fills the complete objects */
    private HttpStatus fillRequest(SurveyRequest surveyRequest) {

        if (!surveyRequest.isValid()) {
            return HttpStatus.BAD_REQUEST;
        }

        Requester requester = requesterService.getById(surveyRequest.getRequester().getId());
        Provider provider = providerService.getById(surveyRequest.getProvider().getId());
        if (requester == null || provider == null) {
            return HttpStatus.NOT_FOUND;
        }

        surveyRequest.setRequester(requester);
        surveyRequest.setProvider(provider);
        return HttpStatus.OK;
    }
}
