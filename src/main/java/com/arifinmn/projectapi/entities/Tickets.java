package com.arifinmn.projectapi.entities;

import com.arifinmn.projectapi.models.EService;
import com.arifinmn.projectapi.models.EStatus;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Services service;

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "status_id", referencedColumnName = "id")
//    private EStatus status;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private Users users;


}
