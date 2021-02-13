package com.arifinmn.projectapi.controllers;

import com.arifinmn.projectapi.entities.Rooms;
import com.arifinmn.projectapi.entities.Schedules;
import com.arifinmn.projectapi.entities.Status;
import com.arifinmn.projectapi.entities.Tickets;
import com.arifinmn.projectapi.exceptions.ApplicationExceptions;
import com.arifinmn.projectapi.exceptions.EntityNotFoundException;
import com.arifinmn.projectapi.models.PageList;
import com.arifinmn.projectapi.models.enums.EStatus;
import com.arifinmn.projectapi.models.requests.ScheduleRequest;
import com.arifinmn.projectapi.models.responses.ResponseMessage;
import com.arifinmn.projectapi.models.searchs.ScheduleSearch;
import com.arifinmn.projectapi.repositories.*;
import com.arifinmn.projectapi.services.RoomService;
import com.arifinmn.projectapi.services.ScheduleService;
import com.arifinmn.projectapi.services.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
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

    @Autowired
    ModelMapper modelMapper;


    @GetMapping()
    public ResponseMessage<PageList<?>> getSchedules(ScheduleSearch search) {
        Schedules request = modelMapper.map(search, Schedules.class);

        Page<Schedules> entityPage = service.findAll(request, search.getPage(),search.getSize(), search.getSort());
        List<Schedules> entityList = entityPage.toList();

        List<Schedules> elements = entityList.stream()
                .map(model -> modelMapper.map(model, Schedules.class))
                .collect(Collectors.toList());

        PageList<Schedules> entities = new PageList<>(
                elements,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements()
        );

        return ResponseMessage.success(entities);
    }

    @PostMapping("/create")
    @Transactional
    public ResponseMessage<?> createSchedule(@RequestBody ScheduleRequest request)  {

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

        Schedules entity = new Schedules(entityTicket, entityRoom);
        if (scheduleOtherRepository.existByTicketId(ticketId)) {
            throw new ApplicationExceptions(HttpStatus.UNPROCESSABLE_ENTITY, "Schedules have already existed for this ticket!");
        }

        entityTicket.setStatus(entityStatus);
        entityTicket = ticketService.save(entityTicket);

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

}
