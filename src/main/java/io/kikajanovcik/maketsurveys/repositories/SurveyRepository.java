package io.kikajanovcik.maketsurveys.repositories;


import io.kikajanovcik.maketsurveys.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
