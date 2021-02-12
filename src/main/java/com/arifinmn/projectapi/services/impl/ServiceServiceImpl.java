package com.arifinmn.projectapi.services.impl;

import com.arifinmn.projectapi.entities.Services;
import com.arifinmn.projectapi.repositories.ServiceRepository;
import com.arifinmn.projectapi.services.ServiceService;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl extends CommonServiceImpl<Services, Integer> implements ServiceService {
    public ServiceServiceImpl(ServiceRepository repository) {
        super(repository);
    }


}