package com.epam.kabaldin.dao.impl;

import com.epam.kabaldin.dao.EventDAO;
import com.epam.kabaldin.model.Entity;
import com.epam.kabaldin.model.Event;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventDAOImpl implements EventDAO {
    private List<Event> eventStorage;
    private Map<String, List<? extends Entity>> storage;

    public void setStorage(Map<String, List<? extends Entity>> storage) {
        this.storage = storage;
    }

    public void init() {
        eventStorage = (List<Event>) storage.computeIfAbsent("event", k -> new ArrayList<>());
    }

    @Override
    public void saveEvent(Event event) {
        eventStorage.add(event);
    }

    @Override
    public Event getEventById(long eventId) {
        for (Event event : eventStorage) {
            if (event.getId() == eventId) {
                return event;
            }
        }
        return null;
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        List<Event> eventsWithTitle = eventStorage.stream()
                .filter(event -> event.getTitle().contains(title))
                .collect(Collectors.toList());

        return getPaginatedList(eventsWithTitle, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        List<Event> eventsForDay = eventStorage.stream()
                .filter(event -> isSameDay(event.getDate(), day))
                .collect(Collectors.toList());

        return getPaginatedList(eventsForDay, pageSize, pageNum);
    }

    private boolean isSameDay(Date date1, Date date2) {
        Instant instant1 = date1.toInstant()
                .truncatedTo(ChronoUnit.DAYS);
        Instant instant2 = date2.toInstant()
                .truncatedTo(ChronoUnit.DAYS);
        return instant1.equals(instant2);
    }

    private List<Event> getPaginatedList(List<Event> events, int pageSize, int pageNum) {
        int startIndex = (pageNum - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, events.size());

        if (startIndex >= events.size()) {
            return Collections.emptyList();
        }
        return events.subList(startIndex, endIndex);
    }


    @Override
    public Event updateEvent(Event event) {
        for (Event eventsForUpdate : eventStorage) {
            if (eventsForUpdate.getId() == event.getId()) {
                eventsForUpdate.setTitle(event.getTitle());
                eventsForUpdate.setDate(event.getDate());
                return eventsForUpdate;
            }
        }
        return null;
    }

    @Override
    public boolean deleteEvent(long eventId) {
        for (Event eventsForDelete : eventStorage) {
            if (eventsForDelete.getId() == eventId) {
                eventStorage.remove(eventsForDelete);
                return true;
            }
        }
        return false;
    }
}

