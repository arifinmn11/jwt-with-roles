package com.arifinmn.projectapi.controllers;

import com.arifinmn.projectapi.entities.Rooms;
import com.arifinmn.projectapi.entities.Tickets;
import com.arifinmn.projectapi.models.requests.RoomRequest;
import com.arifinmn.projectapi.models.responses.ResponseMessage;
import com.arifinmn.projectapi.repositories.TicketRepository;
import com.arifinmn.projectapi.services.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tickets")


public class TicketController {

    @Autowired
    TicketService service;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseMessage<?> createRoom(@RequestBody @Valid Tickets request) {

        Tickets entity = service.save(request);
        return ResponseMessage.success(entity);
    }
}
