package com.epam.kabaldin.model.impl;

import com.epam.kabaldin.model.Entity;
import com.epam.kabaldin.model.Ticket;

public class TicketImpl implements Ticket {
    private long id;
    private long eventId;
    private long userId;
    private Category category;
    private int place;

    public TicketImpl(long id, long eventId, long userId, Category category, int place) {
        this.id = id;
        this.category = category;
        this.eventId = eventId;
        this.userId = userId;
        this.place = place;
    }

    public TicketImpl(long userId, long eventId, int place, Category category) {
        this.category = category;
        this.eventId = eventId;
        this.userId = userId;
        this.place = place;
    }

    public TicketImpl() {

    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getEventId() {
        return eventId;
    }

    @Override
    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int getPlace() {
        return place;
    }

    @Override
    public void setPlace(int place) {
        this.place = place;
    }
}
