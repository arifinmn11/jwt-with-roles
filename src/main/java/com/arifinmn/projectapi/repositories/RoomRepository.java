package com.arifinmn.projectapi.repositories;

import com.arifinmn.projectapi.entities.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Rooms, Integer> {
}
