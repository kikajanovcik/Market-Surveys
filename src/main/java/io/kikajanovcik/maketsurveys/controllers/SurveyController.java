package io.kikajanovcik.maketsurveys.controllers;

import io.kikajanovcik.maketsurveys.classes.SurveyRequest;
import io.kikajanovcik.maketsurveys.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/surveys")
public class SurveyController {

    @Autowired SurveyService surveyService;

    private static final String BAD_REQUEST_MESSAGE = "Sorry, we cannot process your request due to invalid data";
    private static final String SUCCESS_MESSAGE = "You have been successfully subscribed";

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public ResponseEntity<Object> subscribe(@RequestBody SurveyRequest surveyRequest) {

        if (surveyRequest.isInvalid()) {
            return new ResponseEntity<>(BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
        }

        surveyService.subscribe(surveyRequest);
        return new ResponseEntity<>(SUCCESS_MESSAGE, HttpStatus.OK);
    }
}
