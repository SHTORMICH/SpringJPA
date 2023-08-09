package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.EventDAO;
import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EventServiceImpl implements EventService {
    private EventDAO eventDAO;

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public Optional<Event> getEventById(Long eventId) {
        return eventDAO.findById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        Pageable pageable = (Pageable) PageRequest.of(pageNum, pageSize);
        return eventDAO.findAllByTitle(title, pageable);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        Pageable pageable = (Pageable) PageRequest.of(pageNum, pageSize);
        return eventDAO.findAllByDay(day, pageable);
    }

    @Override
    public Event createEvent(Event event) {
        eventDAO.save(event);
        return event;
    }

    @Override
    public Event updateEvent(Event event) {
        eventDAO.save(event);
        return event;
    }

    @Override
    public boolean deleteEvent(Long eventId) {
        eventDAO.deleteById(eventId);
        return true;
    }
}
