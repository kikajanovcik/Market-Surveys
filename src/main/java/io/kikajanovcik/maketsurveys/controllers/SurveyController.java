package io.kikajanovcik.maketsurveys.controllers;

import io.kikajanovcik.maketsurveys.services.SurveyService;
import io.kikajanovcik.maketsurveys.entities.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/surveys")
public class SurveyController {

    @Autowired private SurveyService surveyService;

    @RequestMapping(value = "/subscribe")
    public ResponseEntity<Object> subscribe(@RequestBody Request request) {

        surveyService.subscribe(request);

        if (request.getResponse().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
