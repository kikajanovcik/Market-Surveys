package io.kikajanovcik.maketsurveys.services;

import io.kikajanovcik.maketsurveys.entities.Provider;
import io.kikajanovcik.maketsurveys.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProviderService {

    @Autowired ProviderRepository providederRepository;

    public Provider getById(Long id) {
        return providederRepository.findOne(id);
    }
}
