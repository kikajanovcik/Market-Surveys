package io.kikajanovcik.maketsurveys;

import io.kikajanovcik.maketsurveys.entities.Provider;
import io.kikajanovcik.maketsurveys.entities.Requester;
import io.kikajanovcik.maketsurveys.entities.Survey;
import io.kikajanovcik.maketsurveys.repositories.ProviderRepository;
import io.kikajanovcik.maketsurveys.repositories.RequesterRepository;
import io.kikajanovcik.maketsurveys.repositories.SurveyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner initData(SurveyRepository surveyRepository,
                                      ProviderRepository providerRepository,
                                      RequesterRepository reqesterRepository) {

        return (args) -> {

            reqesterRepository.save(new Requester("Caravelo", new HashMap<String, String>(){{
                put("email","caravelo@caravelo.com");
                put("api","caravelo.com/api/surveys");}}));

            Provider p1 = providerRepository.save(new Provider("Provider1"));
            Provider p2 = providerRepository.save(new Provider("Provider2"));
            Provider p3 = providerRepository.save(new Provider("Provider3"));
            Provider p4 = providerRepository.save(new Provider("Provider4"));

            surveyRepository.save(new Survey(p1, "Food", "ES", new Integer[]{ 20, 40}));
            surveyRepository.save(new Survey(p1, "Food", "ES", new Integer[]{ 30, 50}));
            surveyRepository.save(new Survey(p2, "Cars", "UK", new Integer[]{ 10, 50}));
            surveyRepository.save(new Survey(p2, "Sport", "FR", new Integer[]{ 25, 45}));
            surveyRepository.save(new Survey(p3, "Food", "ES", new Integer[]{ 30, 35}));
            surveyRepository.save(new Survey(p3, "Sport", "FR", new Integer[]{ 20, 50}));
            surveyRepository.save(new Survey(p4, "Cars", "UK", new Integer[]{ 25, 35}));
            surveyRepository.save(new Survey(p4, "Food", "FR", new Integer[]{ 18, 30}));
        };
    }
}