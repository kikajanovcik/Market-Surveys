package io.kikajanovcik.maketsurveys.services;

import io.kikajanovcik.maketsurveys.entities.Provider;
import io.kikajanovcik.maketsurveys.entities.Survey;
import io.kikajanovcik.maketsurveys.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SurveyService {

    @Autowired SurveyRepository surveyRepository;

    public List<Survey> getSurveys(Provider provider, Survey surveyFilter) {

        String subject = surveyFilter.getSubject();
        String country = surveyFilter.getCountry();
        Integer minAge = surveyFilter.getMinAge();
        Integer maxAge = surveyFilter.getMaxAge();

        return surveyRepository.findAll().stream()
                .filter(s ->
                        (s.getProvider().getId().equals(provider.getId())) &&
                        (isNotSelected(subject) || s.getSubject().equalsIgnoreCase(subject)) &&
                        (isNotSelected(country) || s.getCountry().equalsIgnoreCase(country)) &&
                        (isNotSelected(minAge) || s.getMinAge() >= minAge) &&
                        (isNotSelected(maxAge) || s.getMaxAge() <= maxAge))
                .collect(Collectors.toList());
    }

    private boolean isNotSelected(Object object){
        return object == null;
    }
}
