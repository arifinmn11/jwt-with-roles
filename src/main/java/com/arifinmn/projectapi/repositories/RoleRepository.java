package com.arifinmn.projectapi.repositories;

import java.util.Optional;

import com.arifinmn.projectapi.entities.Roles;
import com.arifinmn.projectapi.models.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(ERole name);
}
