package com.arifinmn.projectapi.controllers;

import com.arifinmn.projectapi.entities.Services;
import com.arifinmn.projectapi.exceptions.ApplicationExceptions;
import com.arifinmn.projectapi.exceptions.EntityNotFoundException;
import com.arifinmn.projectapi.models.enums.EService;
import com.arifinmn.projectapi.models.requests.TicketRequest;
import com.arifinmn.projectapi.repositories.ServiceOptionalRepository;
import com.arifinmn.projectapi.entities.Tickets;
import com.arifinmn.projectapi.models.responses.ResponseMessage;
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
    ServiceOptionalRepository serviceRepository;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping()
    public ResponseMessage<?> createRoom(@RequestBody TicketRequest request) {
        String strServiceRequest = request.getService();
        Services entityService;

        if (strServiceRequest == null) {
            throw new EntityNotFoundException();
        }
        switch (strServiceRequest) {
            case "REGISTRATION_SERVICE":
                entityService = serviceRepository.searchByService(EService.REGISTRATION_SERVICE);
                break;
            case "EXTEND_SERVICE":
                entityService = serviceRepository.searchByService(EService.EXTEND_SERVICE);
                break;
            case "COMPLAINT_SERVICE":
                entityService = serviceRepository.searchByService(EService.COMPLAINT_SERVICE);
                break;
            default:
                throw new EntityNotFoundException();
        }

        return ResponseMessage.success(entityService);
    }
}
