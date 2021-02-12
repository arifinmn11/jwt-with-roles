package com.arifinmn.projectapi.entities;

import com.arifinmn.projectapi.models.ERole;
import com.arifinmn.projectapi.models.EService;

import javax.persistence.*;

@Entity
@Table(name = "customer_services")
public class CustomerServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Tickets ticket;

    @OneToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Rooms room;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tickets getTicket() {
        return ticket;
    }

    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
    }

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }
}
