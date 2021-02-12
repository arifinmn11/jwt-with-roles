package com.arifinmn.projectapi.repositories;

import com.arifinmn.projectapi.entities.CustomerServices;
import com.arifinmn.projectapi.entities.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Rooms, Integer> {
}