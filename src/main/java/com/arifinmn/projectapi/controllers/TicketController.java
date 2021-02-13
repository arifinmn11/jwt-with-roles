package com.arifinmn.projectapi.controllers;

import com.arifinmn.projectapi.entities.*;
import com.arifinmn.projectapi.exceptions.EntityNotFoundException;
import com.arifinmn.projectapi.models.enums.EService;
import com.arifinmn.projectapi.models.enums.EStatus;
import com.arifinmn.projectapi.models.requests.TicketRequest;
import com.arifinmn.projectapi.models.responses.TicketResponse;
import com.arifinmn.projectapi.repositories.ServiceOtherRepository;
import com.arifinmn.projectapi.models.responses.ResponseMessage;
import com.arifinmn.projectapi.repositories.StatusOtherRepository;
import com.arifinmn.projectapi.repositories.UserRepository;
import com.arifinmn.projectapi.securities.jwt.JwtUtils;
import com.arifinmn.projectapi.services.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    TicketService service;

    @Autowired
    ServiceOtherRepository serviceRepository;

    @Autowired
    StatusOtherRepository statusRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseMessage<Tickets> createRoom(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody TicketRequest request) {
        String strServiceRequest = request.getService();
        String strStatusRequest = request.getStatus();

        Services entityService;
        Status entityStatus;
        Tickets model = new Tickets();


        String token = jwtUtils.splitTokenFromBearer(bearerToken);
        String strUserId = jwtUtils.getUserIdFromJwtToken(token);
        Long userId = Long.parseLong(strUserId);

        Users users = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("your token wrong!"));


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

        if (strStatusRequest == null) {
            entityStatus = statusRepository.searchByStatus(EStatus.PENDING);
        } else {
            switch (strStatusRequest) {
                case "PENDING":
                    entityStatus = statusRepository.searchByStatus(EStatus.PENDING);
                    break;
                case "PROCESSING":
                    entityStatus = statusRepository.searchByStatus(EStatus.PROCESSING);
                    break;
                case "APPROVED":
                    entityStatus = statusRepository.searchByStatus(EStatus.APPROVED);
                    break;
                case "REJECTED":
                    entityStatus = statusRepository.searchByStatus(EStatus.REJECTED);
                    break;
                default:
                    throw new EntityNotFoundException();
            }
        }

        model.setUsers(users);
        model.setService(entityService);
        model.setStatus(entityStatus);

        Tickets entity = service.save(model);

        return ResponseMessage.success(entity);
    }

    @GetMapping("/{id}/get")
    public ResponseMessage<TicketResponse> getTicketById(@PathVariable Integer id) {
        Tickets entity = service.findById(id);

        if (entity == null) {
            throw new EntityNotFoundException();
        }

        String status = entity.getStatus().getStatus().toString();
        String service = entity.getService().getService().toString();
        Users users = entity.getUsers();

        TicketResponse response = new TicketResponse(id, status, service, users);

        return ResponseMessage.success(response);
    }

    @PutMapping("{id}/update")
    public ResponseMessage<Tickets> getTicketById(
            @PathVariable Integer id,
            @RequestBody TicketRequest request) {

        String strServiceRequest = request.getService();
        String strStatusRequest = request.getStatus();
        Services entityService;
        Status entityStatus;

        Tickets model = service.findById(id);
        if (model == null) {
            throw new EntityNotFoundException();
        }

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

        if (strStatusRequest == null) {
            entityStatus = statusRepository.searchByStatus(EStatus.PENDING);
        } else {
            switch (strStatusRequest) {
                case "PENDING":
                    entityStatus = statusRepository.searchByStatus(EStatus.PENDING);
                    break;
                case "PROCESSING":
                    entityStatus = statusRepository.searchByStatus(EStatus.PROCESSING);
                    break;
                case "APPROVED":
                    entityStatus = statusRepository.searchByStatus(EStatus.APPROVED);
                    break;
                case "REJECTED":
                    entityStatus = statusRepository.searchByStatus(EStatus.REJECTED);
                    break;
                default:
                    throw new EntityNotFoundException();
            }
        }

        model.setStatus(entityStatus);
        model.setService(entityService);

        Tickets entity = service.save(model);

        return ResponseMessage.success(entity);
    }

    @DeleteMapping("{id}/delete")
    public ResponseMessage<Tickets> deleteTicketById(@PathVariable Integer id) {
        Tickets entity = service.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(entity);
    }
}
