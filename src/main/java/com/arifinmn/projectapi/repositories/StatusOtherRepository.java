package com.arifinmn.projectapi.repositories;

import com.arifinmn.projectapi.entities.Services;
import com.arifinmn.projectapi.entities.Status;
import com.arifinmn.projectapi.models.enums.EService;
import com.arifinmn.projectapi.models.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatusOtherRepository extends JpaRepository<Status, String> {
    @Query("SELECT e FROM Status e WHERE e.status = ?1")
    Status searchByStatus(@Param("status") EStatus status);
}
