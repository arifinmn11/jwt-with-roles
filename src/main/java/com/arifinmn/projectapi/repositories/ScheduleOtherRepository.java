package com.arifinmn.projectapi.repositories;

import com.arifinmn.projectapi.models.responses.ScheduleResponse;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleOtherRepository {
    ScheduleResponse findByTicketId(Integer id);
    Boolean existByTicketId(Integer id);
}
