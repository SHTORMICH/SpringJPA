package com.epam.kabaldin.model.impl;

import com.epam.kabaldin.model.Entity;
import com.epam.kabaldin.model.Event;

import java.util.Date;

public class EventImpl implements Event {
    private long id;
    private String title;
    private Date date;

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
}
