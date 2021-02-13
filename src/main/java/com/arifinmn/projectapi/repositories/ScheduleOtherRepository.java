package com.arifinmn.projectapi.repositories;

import com.arifinmn.projectapi.entities.Schedules;
import com.arifinmn.projectapi.entities.Services;
import com.arifinmn.projectapi.entities.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleOtherRepository extends JpaRepository<Schedules, Integer> {
//    @Query("SELECT e FROM Schedules e WHERE e.ticket.id =:ticket")
//    Optional<?> searchByTicketId(@Param("ticket") Integer ticket);

//    @Query(value = "SELECT * FROM schedules", nativeQuery = true)
//    Optional<?> searchByTicketId(Integer ticket);

//    @Query(
//            value = "SELECT * FROM schedules where ticket_id =:ticket",
//            nativeQuery = true)
//    Optional<?> searchByTicketId(@Param("ticket")Integer ticket);
}
