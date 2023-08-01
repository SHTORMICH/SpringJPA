package com.epam.kabaldin.dao;

import com.epam.kabaldin.model.Event;

import java.util.Date;
import java.util.List;

public interface EventDAO {
    void saveEvent(Event event);
    Event getEventById(long eventId);
    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);
    List<Event> getEventsForDay(Date day, int pageSize, int pageNum);
    Event updateEvent(Event event);
    boolean deleteEvent(long eventId);
}
