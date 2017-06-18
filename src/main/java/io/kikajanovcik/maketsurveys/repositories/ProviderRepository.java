package io.kikajanovcik.maketsurveys.repositories;

import io.kikajanovcik.maketsurveys.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
