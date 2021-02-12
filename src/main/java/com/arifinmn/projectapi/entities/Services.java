package com.arifinmn.projectapi.entities;

import com.arifinmn.projectapi.models.EService;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EService service;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EService getService() {
        return service;
    }

    public void setService(EService service) {
        this.service = service;
    }
}
