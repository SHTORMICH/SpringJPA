package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.EventDAO;
import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.service.EventService;

import java.util.Date;
import java.util.List;

public class EventServiceImpl implements EventService {
    private EventDAO eventDAO;

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public Event getEventById(long eventId) {
        return eventDAO.getEventById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventDAO.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventDAO.getEventsForDay(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        eventDAO.saveEvent(event);
        return event;
    }

    @Override
    public Event updateEvent(Event event) {
        eventDAO.updateEvent(event);
        return event;
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventDAO.deleteEvent(eventId);
    }
}
