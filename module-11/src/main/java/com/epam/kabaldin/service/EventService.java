package com.epam.kabaldin.service;

import com.epam.kabaldin.model.Event;

import java.util.Date;
import java.util.List;

public interface EventService {
    public Event getEventById(long eventId);
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum);
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum);
    public Event createEvent(Event event);
    public Event updateEvent(Event event);
    public boolean deleteEvent(long eventId);
}
