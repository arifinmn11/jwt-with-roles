package com.arifinmn.projectapi.models.requests;

import com.arifinmn.projectapi.entities.Services;
import com.arifinmn.projectapi.entities.Status;
import com.arifinmn.projectapi.entities.Users;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class TicketRequest {
    private Integer id;
    private String service;
    private String status;
    private Integer user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
