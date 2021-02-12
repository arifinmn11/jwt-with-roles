package com.arifinmn.projectapi.services.impl;

import com.arifinmn.projectapi.entities.Rooms;
import com.arifinmn.projectapi.repositories.RoomRepository;
import com.arifinmn.projectapi.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl extends CommonServiceImpl<Rooms, Integer> implements RoomService {
    public RoomServiceImpl(RoomRepository repository) {
        super(repository);
    }
}
