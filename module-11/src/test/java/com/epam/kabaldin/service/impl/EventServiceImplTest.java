package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.EventDAO;
import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.impl.EventImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class EventServiceImplTest {
    @Mock
    private EventDAO eventDAO;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEvent() {
        Event event = new EventImpl();
        event.setId(1L);
        event.setTitle("Sample Event");
        event.setDate(new Date());

        Event savedEvent = eventService.createEvent(event);

        verify(eventDAO, times(1)).saveEvent(event);

        assertEquals(event, savedEvent);
    }

    @Test
    void testUpdateEvent() {
        Event event = new EventImpl();
        event.setId(1L);
        event.setTitle("Sample Event");
        event.setDate(new Date());

        Event updatedEvent = eventService.updateEvent(event);

        verify(eventDAO, times(1)).updateEvent(event);

        assertEquals(event, updatedEvent);
    }

    @Test
    void testGetEventById() {
        long eventId = 1L;
    Event expectedEvent = new EventImpl();
        expectedEvent.setId(eventId);
        expectedEvent.setTitle("Sample Event");
        expectedEvent.setDate(new Date());

    when(eventDAO.getEventById(eventId)).thenReturn(expectedEvent);

    Event retrievedEvent = eventService.getEventById(eventId);

    verify(eventDAO, times(1)).getEventById(eventId);

    assertEquals(expectedEvent, retrievedEvent);
}

    @Test
    void testGetEventsByTitle() {
        String title = "Sample Event";
        int pageSize = 10;
        int pageNum = 1;
        List<Event> expectedEvents = new ArrayList<>();

        when(eventDAO.getEventsByTitle(title, pageSize, pageNum)).thenReturn(expectedEvents);

        List<Event> retrievedEvents = eventService.getEventsByTitle(title, pageSize, pageNum);

        verify(eventDAO, times(1)).getEventsByTitle(title, pageSize, pageNum);

        assertEquals(expectedEvents, retrievedEvents);
    }

    @Test
    void testGetEventsForDay() {
        Date day = new Date();
        int pageSize = 10;
        int pageNum = 1;
        List<Event> expectedEvents = new ArrayList<>();

        when(eventDAO.getEventsForDay(day, pageSize, pageNum)).thenReturn(expectedEvents);

        List<Event> retrievedEvents = eventService.getEventsForDay(day, pageSize, pageNum);

        verify(eventDAO, times(1)).getEventsForDay(day, pageSize, pageNum);

        assertEquals(expectedEvents, retrievedEvents);
    }

    @Test
    void testDeleteEvent() {
        long eventId = 1L;

        when(eventDAO.deleteEvent(eventId)).thenReturn(true);

        boolean result = eventService.deleteEvent(eventId);

        verify(eventDAO, times(1)).deleteEvent(eventId);

        assertTrue(result);
    }

}
