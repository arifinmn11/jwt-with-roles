package com.arifinmn.projectapi.services.impl;

import com.arifinmn.projectapi.entities.Schedules;
import com.arifinmn.projectapi.repositories.ScheduleRepository;
import com.arifinmn.projectapi.services.ScheduleService;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl extends CommonServiceImpl<Schedules, Integer> implements ScheduleService {
    public ScheduleServiceImpl(ScheduleRepository repository) {
        super(repository);
    }
}