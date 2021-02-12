package com.arifinmn.projectapi.controllers;

import com.arifinmn.projectapi.entities.Rooms;
import com.arifinmn.projectapi.exceptions.EntityNotFoundException;
import com.arifinmn.projectapi.models.requests.RoomRequest;
import com.arifinmn.projectapi.models.responses.ResponseMessage;
import com.arifinmn.projectapi.repositories.RoomRepository;
import com.arifinmn.projectapi.services.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService service;

    @Autowired
    private RoomRepository repository;

    @Autowired
    ModelMapper modelMapper;

//    @GetMapping()
//    public ResponseEntity<?> findRooms(@RequestBody Rooms request) {
//        Rooms entity = service.save(request);
//        return ResponseEntity.ok(entity);
//    }

    @PostMapping("/create")
    public ResponseMessage<Rooms> createRoom(@RequestBody @Valid RoomRequest request) {
        Rooms model = modelMapper.map(request, Rooms.class);

        Rooms entity = service.save(model);
        return ResponseMessage.success(entity);
    }

    @GetMapping("/{id}/get")
    public ResponseMessage<Rooms> getRoomById(@PathVariable Integer id) {
        Rooms entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        return ResponseMessage.success(entity);
    }

    @PutMapping("/{id}/update")
    public ResponseMessage<Rooms> updateRoomById(
            @PathVariable Integer id,
            @RequestBody RoomRequest request) {
        Rooms entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        entity.setRoom(request.getRoom());
        entity.setActive(request.getActive());
        entity = service.save(entity);

        return ResponseMessage.success(entity);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseMessage<Rooms> deleteRoomById(@PathVariable Integer id) {
        Rooms entity = service.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(entity);
    }

}
