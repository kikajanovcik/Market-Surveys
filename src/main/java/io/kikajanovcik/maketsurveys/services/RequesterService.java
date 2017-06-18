package io.kikajanovcik.maketsurveys.services;

import io.kikajanovcik.maketsurveys.entities.Requester;
import io.kikajanovcik.maketsurveys.repositories.RequesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RequesterService {

    @Autowired RequesterRepository requesterRepository;

    public Requester getById(Long id) {
        return requesterRepository.findOne(id);
    }
}
