package com.arifinmn.projectapi.repositories;

import com.arifinmn.projectapi.entities.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedules, Integer> {
}
