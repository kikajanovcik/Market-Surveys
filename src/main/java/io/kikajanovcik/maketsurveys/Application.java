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

            Provider p1 = new Provider("provider1");
            Provider p2 = new Provider("provider2");
            Provider p3 = new Provider("provider3");
            Provider p4 = new Provider("provider4");
            providerRepository.save(p1);
            providerRepository.save(p2);
            providerRepository.save(p3);
            providerRepository.save(p4);

            Survey s1 = new Survey(p1, "Food", "ES");
            Survey s2 = new Survey(p1, "Cars", "ES");
            Survey s3 = new Survey(p1, "Sport", "FR");
            Survey s4 = new Survey(p1, "Food", "UK");
            surveyRepository.save(s1);
            surveyRepository.save(s2);
            surveyRepository.save(s3);
            surveyRepository.save(s4);

        };
    }
}