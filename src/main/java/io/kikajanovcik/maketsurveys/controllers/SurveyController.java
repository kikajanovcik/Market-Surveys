package io.kikajanovcik.maketsurveys.controllers;

import io.kikajanovcik.maketsurveys.classes.SurveyRequest;
import io.kikajanovcik.maketsurveys.entities.Provider;
import io.kikajanovcik.maketsurveys.entities.Requester;
import io.kikajanovcik.maketsurveys.services.ProviderService;
import io.kikajanovcik.maketsurveys.services.RequesterService;
import io.kikajanovcik.maketsurveys.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/surveys")
public class SurveyController {

    @Autowired SurveyService surveyService;
    @Autowired RequesterService requesterService;
    @Autowired ProviderService providerService;

    private static final String BAD_REQUEST_MESSAGE = "Process interrupted due to invalid data";
    private static final String NOT_FOUND_MESSAGE = "Process interrupted due to incorrect data";
    private static final String SUCCESS_MESSAGE = "You have been successfully subscribed";

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public ResponseEntity<Object> subscribe(@RequestBody SurveyRequest surveyRequest) {

        if (!surveyRequest.isValid()) {
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
        }

        Requester requester = requesterService.getRequester(surveyRequest.getRequester().getId());
        Provider provider = providerService.getProvider(surveyRequest.getProvider().getId());
        if (requester == null || provider == null) {
            return new ResponseEntity<>(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
        }

        surveyRequest.setRequester(requester);
        surveyRequest.setProvider(provider);
        surveyService.subscribe(surveyRequest);
        return new ResponseEntity<>(SUCCESS_MESSAGE, HttpStatus.OK);
    }
}
