package com.epam.kabaldin.model.impl;

import com.epam.kabaldin.model.Event;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table( name = "event" )
public class EventImpl implements Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "date")

    private Date date;

    @Column(name = "ticket_price")
    private Long ticketPrice;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Long getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public void setTicketPrice(Long ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
