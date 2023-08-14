package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.EventDAO;
import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.impl.EventImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventServiceImplTest {

    @Mock
    private EventDAO eventDAO;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEventById() {
        Long eventId = 1L;
        Event event = new EventImpl();
        when(eventDAO.findById(eventId)).thenReturn(Optional.of((EventImpl) event));

        Event result = eventService.getEventById(eventId);

        assertNotNull(result);
        assertEquals(event, result);
    }

    @Test
    public void testGetEventsByTitle() {
        String title = "Test Event";
        int pageSize = 10;
        int pageNum = 0;
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<Event> events = new ArrayList<>();
        when(eventDAO.findAllByTitle(title, pageable)).thenReturn(events);

        List<Event> result = eventService.getEventsByTitle(title, pageSize, pageNum);

        assertNotNull(result);
        assertEquals(events, result);
    }

    @Test
    public void testGetEventsForDay() {
        Date date = new Date();
        int pageSize = 10;
        int pageNum = 0;
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<Event> events = new ArrayList<>();
        when(eventDAO.findAllByDate(date, pageable)).thenReturn(events);

        List<Event> result = eventService.getEventsForDay(date, pageSize, pageNum);

        assertNotNull(result);
        assertEquals(events, result);
    }

    @Test
    public void testCreateEvent() {
        Event event = new EventImpl();

        Event result = eventService.createEvent(event);

        assertNotNull(result);
        assertEquals(event, result);
        verify(eventDAO, times(1)).save(event);
    }

    @Test
    public void testUpdateEvent() {
        Event event = new EventImpl();

        Event result = eventService.updateEvent(event);

        assertNotNull(result);
        assertEquals(event, result);
        verify(eventDAO, times(1)).save(event);
    }

    @Test
    public void testDeleteEvent() {
        Long eventId = 1L;

        boolean result = eventService.deleteEvent(eventId);

        assertTrue(result);
        verify(eventDAO, times(1)).deleteById(eventId);
    }
}
