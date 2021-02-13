package com.arifinmn.projectapi.models.responses;

import com.arifinmn.projectapi.entities.Users;

public class TicketResponse {
    private Integer id;
    private String status;
    private String service;
    private Users users;

    public TicketResponse() {
    }

    public TicketResponse(Integer id, String status, String service, Users users) {
        this.id = id;
        this.status = status;
        this.service = service;
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
