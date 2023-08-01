package com.epam.kabaldin.dao.impl;

import com.epam.kabaldin.model.Entity;
import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.impl.EventImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EventDAOImplTest {
    private final Map<String, List<? extends Entity>> storage = new HashMap<>();
    private List<Event> eventStorage;
    private EventDAOImpl eventDAO;

    @BeforeEach
    void setUp() {
        eventDAO = new EventDAOImpl();
        eventStorage = new ArrayList<>();
        Event event = new EventImpl();
        event.setId(1L);
        event.setTitle("Sample Event");
        event.setDate(new Date());

        eventStorage.add(event);
        storage.put("event", eventStorage);
        eventDAO.setStorage(storage);
        eventDAO.init();
    }

    @Test
    void testSaveEvent() {
        Event event = new EventImpl();
        event.setId(2L);
        event.setTitle("Sample Event");
        event.setDate(new Date());

        eventDAO.saveEvent(event);

        assertEquals(2, eventStorage.size());
        assertTrue(eventStorage.contains(event));
    }

    @Test
    void testGetEventById() {
        Event event2 = new EventImpl();
        event2.setId(2L);
        event2.setTitle("Sample Event");
        event2.setDate(new Date());

        eventDAO.saveEvent(event2);
        eventDAO.getEventById(1L);

        assertEquals(event2, eventDAO.getEventById(2L));
    }

    @Test
    void testGetEventsByTitle() {
        Event event2 = new EventImpl();
        event2.setId(2L);
        event2.setTitle("Sample Event 2");
        event2.setDate(new Date());

        eventDAO.saveEvent(event2);

        List<Event> retrievedEvents = eventDAO.getEventsByTitle("Event", 10, 1);

        assertEquals(2, retrievedEvents.size());
        assertEquals(event2, eventDAO.getEventsByTitle("Sample Event 2",10, 1).get(0));
    }

    @Test
    void testGetEventsForDay() {
        Date day = new Date();

        Event event2 = new EventImpl();
        event2.setId(2L);
        event2.setTitle("Sample Event 2");
        event2.setDate(day);
        eventDAO.saveEvent(event2);

        List<Event> retrievedEvents = eventDAO.getEventsForDay(day, 10, 1);

        assertEquals(2, retrievedEvents.size());
        assertTrue(retrievedEvents.contains(eventDAO.getEventById(1L)));
        assertTrue(retrievedEvents.contains(event2));
    }

    @Test
    void testUpdateEvent() {
        Event updatedEvent = new EventImpl();
        updatedEvent.setId(1L);
        updatedEvent.setTitle("Updated Event");
        updatedEvent.setDate(new Date());

        Event retrievedEvent = eventDAO.updateEvent(updatedEvent);

        assertEquals("Updated Event", eventStorage.get(0).getTitle());
    }
}

