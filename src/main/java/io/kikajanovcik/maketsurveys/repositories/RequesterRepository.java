package io.kikajanovcik.maketsurveys.repositories;

import io.kikajanovcik.maketsurveys.entities.Requester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface RequesterRepository extends JpaRepository<Requester, Long> {
}

