package com.epam.kabaldin.controller;

import com.epam.kabaldin.facade.BookingFacade;
import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.model.impl.EventImpl;
import com.epam.kabaldin.model.impl.TicketImpl;
import com.epam.kabaldin.model.impl.UserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class BookingFacadeControllerTest {

    @Mock
    private BookingFacade bookingFacade;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEventById() {
        long eventId = 1;
        Event event = new EventImpl();
        event.setId(1);
        event.setTitle("Test Event");
        event.setDate(new Date());

        when(bookingFacade.getEventById(eventId)).thenReturn(event);

        ModelAndView modelAndView = bookingController.getEventById(eventId);
        assertEquals("event", modelAndView.getViewName());
        assertEquals(event, modelAndView.getModel().get("event"));
    }

    @Test
    void testGetEventsByTitle() {
        String title = "Event";
        int pageSize = 10;
        int pageNum = 1;
        List<Event> events = new ArrayList<>();
        Event event1 = new EventImpl();
        event1.setId(1);
        event1.setTitle("Event 1");
        event1.setDate(new Date());
        Event event2 = new EventImpl();
        event2.setId(2);
        event2.setTitle("Event 2");
        event2.setDate(new Date());
        events.add(event1);
        events.add(event2);

        when(bookingFacade.getEventsByTitle(title, pageSize, pageNum)).thenReturn(events);

        ModelAndView modelAndView = bookingController.getEventsByTitle(title, pageSize, pageNum);
        assertEquals("events", modelAndView.getViewName());
        assertEquals(events, modelAndView.getModel().get("events"));
    }

    @Test
    void testGetEventsForDay() {
        Date day = new Date();
        int pageSize = 10;
        int pageNum = 1;
        List<Event> events = new ArrayList<>();
        Event event1 = new EventImpl();
        event1.setId(1);
        event1.setTitle("Event 1");
        event1.setDate(new Date());
        Event event2 = new EventImpl();
        event2.setId(2);
        event2.setTitle("Event 2");
        event2.setDate(new Date());
        events.add(event1);
        events.add(event2);

        when(bookingFacade.getEventsForDay(day, pageSize, pageNum)).thenReturn(events);

        ModelAndView modelAndView = bookingController.getEventsForDay(day, pageSize, pageNum);
        assertEquals("events", modelAndView.getViewName());
        assertEquals(events, modelAndView.getModel().get("events"));
    }

    @Test
    void testCreateEvent() {
        Event event = new EventImpl();
        event.setId(1);
        event.setTitle("Test Event");
        event.setDate(new Date());

        when(bookingFacade.createEvent(any())).thenReturn(event);

        Event createdEvent = bookingController.createEvent(event);
        assertEquals(event, createdEvent);
    }

    @Test
    void testUpdateEvent() {
        Event event = new EventImpl();
        event.setId(1);
        event.setTitle("Updated Event Name");
        event.setDate(new Date());
        when(bookingFacade.updateEvent(any())).thenReturn(event);

        Event updatedEvent = bookingController.updateEvent(event);
        assertEquals(event, updatedEvent);
    }

    @Test
    void testDeleteEvent() {
        long eventId = 1;

        when(bookingFacade.deleteEvent(eventId)).thenReturn(true);

        boolean result = bookingController.deleteEvent(eventId);
        assertTrue(result);
    }

    @Test
    void testGetUserById() {
        long userId = 1;
        User user = new UserImpl();
        user.setId(userId);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        when(bookingFacade.getUserById(userId)).thenReturn(user);

        ModelAndView modelAndView = bookingController.getUserById(userId);
        assertEquals("user", modelAndView.getViewName());
        assertEquals(user, modelAndView.getModel().get("user"));
    }

    @Test
    void testGetUsersByName() {
        String name = "John";
        int pageSize = 10;
        int pageNum = 1;
        List<User> users = new ArrayList<>();
        User user1 = new UserImpl();
        user1.setId(1);
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");
        User user2 = new UserImpl();
        user2.setId(2);
        user2.setName("John Smith");
        user2.setEmail("john.smith@example.com");
        users.add(user1);
        users.add(user2);

        when(bookingFacade.getUsersByName(name, pageSize, pageNum)).thenReturn(users);

        ModelAndView modelAndView = bookingController.getUsersByName(name, pageSize, pageNum);
        assertEquals("users", modelAndView.getViewName());
        assertEquals(users, modelAndView.getModel().get("users"));
    }

    @Test
    void testCreateUser() {
        User user = new UserImpl();
        user.setId(1);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        when(bookingFacade.createUser(any())).thenReturn(user);

        User createdUser = bookingController.createUser(user);
        assertEquals(user, createdUser);
    }

    @Test
    void testUpdateUser() {
        User user = new UserImpl();
        user.setId(1);
        user.setName("Updated John Doe");
        user.setEmail("john.doe@example.com");

        when(bookingFacade.updateUser(any())).thenReturn(user);

        User updatedUser = bookingController.updateUser(user);
        assertEquals(user, updatedUser);
    }

    @Test
    void testDeleteUser() {
        long userId = 1;

        when(bookingFacade.deleteUser(userId)).thenReturn(true);

        boolean result = bookingController.deleteUser(userId);
        assertTrue(result);
    }

    @Test
    void testBookTicket() {
        long userId = 1;
        long eventId = 1;
        int place = 10;
        Ticket.Category category = Ticket.Category.PREMIUM;
        Ticket ticket = new TicketImpl();
        ticket.setUserId(userId);
        ticket.setEventId(eventId);
        ticket.setCategory(category);
        ticket.setPlace(place);

        when(bookingFacade.bookTicket(userId, eventId, place, category)).thenReturn(ticket);

        Ticket bookedTicket = bookingController.bookTicket(ticket);
        assertEquals(ticket, bookedTicket);
    }

    @Test
    void testGetBookedTickets() {
        long userId = 1;
        int pageSize = 10;
        int pageNum = 1;
        User user = new UserImpl();
        user.setId(1);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket1 = new TicketImpl();
        ticket1.setUserId(userId);
        ticket1.setEventId(1);
        ticket1.setCategory(Ticket.Category.PREMIUM);
        ticket1.setPlace(10);
        Ticket ticket2 = new TicketImpl();
        ticket2.setUserId(userId);
        ticket2.setEventId(2);
        ticket2.setCategory(Ticket.Category.STANDARD);
        ticket2.setPlace(20);
        tickets.add(ticket1);
        tickets.add(ticket2);

        when(bookingFacade.getUserById(userId)).thenReturn(user);
        when(bookingFacade.getBookedTickets(user, pageSize, pageNum)).thenReturn(tickets);

        ModelAndView modelAndView = bookingController.getBookedTickets(userId, pageSize, pageNum);
        assertEquals("tickets", modelAndView.getViewName());
        assertEquals(tickets, modelAndView.getModel().get("tickets"));
    }

    @Test
    void testGetBookedTicketsByEvent() {
        long eventId = 1;
        int pageSize = 10;
        int pageNum = 1;
        Event event = new EventImpl();
        event.setId(eventId);
        event.setTitle("Event Name");
        event.setDate(new Date());
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket1 = new TicketImpl();
        ticket1.setUserId(1L);
        ticket1.setEventId(1);
        ticket1.setCategory(Ticket.Category.PREMIUM);
        ticket1.setPlace(10);
        Ticket ticket2 = new TicketImpl();
        ticket2.setUserId(2L);
        ticket2.setEventId(2);
        ticket2.setCategory(Ticket.Category.STANDARD);
        ticket2.setPlace(20);
        tickets.add(ticket1);
        tickets.add(ticket2);

        when(bookingFacade.getEventById(eventId)).thenReturn(event);
        when(bookingFacade.getBookedTickets(event, pageSize, pageNum)).thenReturn(tickets);

        ModelAndView modelAndView = bookingController.getBookedTicketsByEvent(eventId, pageSize, pageNum);
        assertEquals("tickets", modelAndView.getViewName());
        assertEquals(tickets, modelAndView.getModel().get("tickets"));
    }

    @Test
    void testCancelTicket() {
        long ticketId = 1;

        when(bookingFacade.cancelTicket(ticketId)).thenReturn(true);

        boolean result = bookingController.cancelTicket(ticketId);
        assertTrue(result);
    }
}
