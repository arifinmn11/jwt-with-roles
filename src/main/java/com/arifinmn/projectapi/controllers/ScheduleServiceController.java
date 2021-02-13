package com.arifinmn.projectapi.controllers;

import com.arifinmn.projectapi.entities.Rooms;
import com.arifinmn.projectapi.entities.Schedules;
import com.arifinmn.projectapi.entities.Status;
import com.arifinmn.projectapi.entities.Tickets;
import com.arifinmn.projectapi.exceptions.ApplicationExceptions;
import com.arifinmn.projectapi.exceptions.EntityNotFoundException;
import com.arifinmn.projectapi.models.enums.EStatus;
import com.arifinmn.projectapi.models.requests.ScheduleRequest;
import com.arifinmn.projectapi.models.responses.ResponseMessage;
import com.arifinmn.projectapi.repositories.*;
import com.arifinmn.projectapi.services.RoomService;
import com.arifinmn.projectapi.services.ScheduleService;
import com.arifinmn.projectapi.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/schedules")
public class ScheduleServiceController {


    @Autowired
    ScheduleService service;

    @Autowired
    StatusOtherRepository statusRepository;

    @Autowired
    TicketService ticketService;

    @Autowired
    RoomService roomService;

    @Autowired
    ScheduleOtherRepository scheduleOtherRepository;


    @PostMapping("/create")
    public ResponseMessage<?> createSchedule(@RequestBody ScheduleRequest request) {

        Integer ticketId = request.getTicket_id();
        Integer roomId = request.getRoom_id();
        Status entityStatus;

        entityStatus = statusRepository.searchByStatus(EStatus.PROCESSING);

        Tickets entityTicket = ticketService.findById(ticketId);
        if (entityTicket == null) {
            throw new EntityNotFoundException();
        }

        Rooms entityRoom = roomService.findById(roomId);
        if (entityRoom == null) {
            throw new EntityNotFoundException();
        }

        entityTicket.setStatus(entityStatus);
        entityTicket = ticketService.save(entityTicket);

        Schedules entity = new Schedules(entityTicket, entityRoom);
        entity = service.save(entity);

        return ResponseMessage.success(entity);
    }

    @GetMapping("/{id}/get")
    public ResponseMessage<?> getScheduleById(@PathVariable Integer id) {
        Schedules entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(entity);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseMessage<?> deleteScheduleById(@PathVariable Integer id) {
        Schedules entity = service.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(entity);
    }

    @PutMapping("/{id}/update")
    public ResponseMessage<?> updateScheduleById(@PathVariable Integer id) {
//        Schedules entity = service.findById(id);
//        if (entity == null) {
//            throw new EntityNotFoundException();
//        }


        return ResponseMessage.success(scheduleOtherRepository.searchByTicketId(id));
    }
}
