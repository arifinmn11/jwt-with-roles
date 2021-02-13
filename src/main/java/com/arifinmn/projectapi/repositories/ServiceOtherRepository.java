package com.arifinmn.projectapi.repositories;

import com.arifinmn.projectapi.entities.Services;
import com.arifinmn.projectapi.models.enums.EService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOtherRepository extends JpaRepository<Services, String> {
    @Query("SELECT e FROM Services e WHERE e.service = ?1")
    Services searchByService(@Param("service") EService service);
}
