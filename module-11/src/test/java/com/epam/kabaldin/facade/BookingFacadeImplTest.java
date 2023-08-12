package com.epam.kabaldin.facade;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.model.UserAccount;
import com.epam.kabaldin.model.impl.EventImpl;
import com.epam.kabaldin.model.impl.TicketImpl;
import com.epam.kabaldin.model.impl.UserAccountImpl;
import com.epam.kabaldin.model.impl.UserImpl;
import com.epam.kabaldin.service.EventService;
import com.epam.kabaldin.service.TicketService;
import com.epam.kabaldin.service.UserAccountService;
import com.epam.kabaldin.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

class BookingFacadeImplTest {

    @Mock
    private UserService userService;

    @Mock
    private EventService eventService;

    @Mock
    private TicketService ticketService;

    @Mock
    private UserAccountService userAccountService;

    private BookingFacadeImpl bookingFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookingFacade = new BookingFacadeImpl(userService, eventService, ticketService, userAccountService);
    }

    @Test
    void testGetEventById() {
        long eventId = 1L;
        Event event = new EventImpl();
        when(eventService.getEventById(eventId)).thenReturn(event);

        Event result = bookingFacade.getEventById(eventId);

        assertEquals(event, result);
    }

    @Test
    void testCreateEvent() {
        Event event = new EventImpl();
        when(eventService.createEvent(event)).thenReturn(event);

        Event result = bookingFacade.createEvent(event);

        assertEquals(event, result);
    }

    @Test
    void testUpdateEvent() {
        Event event = new EventImpl();
        when(eventService.updateEvent(event)).thenReturn(event);

        Event result = bookingFacade.updateEvent(event);

        assertEquals(event, result);
    }

    @Test
    void testDeleteEvent() {
        long eventId = 1L;
        when(eventService.deleteEvent(eventId)).thenReturn(true);

        boolean result = bookingFacade.deleteEvent(eventId);

        assertTrue(result);
    }

    @Test
    void testGetUserById() {
        long userId = 1L;
        User user = new UserImpl();
        when(userService.getUserById(userId)).thenReturn(user);

        User result = bookingFacade.getUserById(userId);

        assertEquals(user, result);
    }

    @Test
    void testGetUserByEmail() {
        String email = "test@example.com";
        User user = new UserImpl();
        when(userService.getUserByEmail(email)).thenReturn(user);

        User result = bookingFacade.getUserByEmail(email);

        assertEquals(user, result);
    }

    @Test
    void testGetUsersByName() {
        String name = "John";
        int pageSize = 10;
        int pageNum = 1;
        List<User> users = Collections.singletonList(new UserImpl());
        when(userService.getUsersByName(name, pageSize, pageNum)).thenReturn(users);

        List<User> result = bookingFacade.getUsersByName(name, pageSize, pageNum);

        assertEquals(users, result);
    }

    @Test
    void testCreateUser() {
        User user = new UserImpl();
        when(userService.createUser(user)).thenReturn(user);

        User result = bookingFacade.createUser(user);

        assertEquals(user, result);
    }

    @Test
    void testUpdateUser() {
        User user = new UserImpl();
        when(userService.updateUser(user)).thenReturn(user);

        User result = bookingFacade.updateUser(user);

        assertEquals(user, result);
    }

    @Test
    void testDeleteUser() {
        long userId = 1L;
        when(userService.deleteUser(userId)).thenReturn(true);

        boolean result = bookingFacade.deleteUser(userId);

        assertTrue(result);
    }

    @Test
    void testGetTicket() {
        Long ticketId = 1L;
        Ticket ticket = new TicketImpl();
        when(ticketService.getTicket(ticketId)).thenReturn(ticket);

        Ticket result = bookingFacade.getTicket(ticketId);

        assertEquals(ticket, result);
    }

    @Test
    void testGetBookedTicketsByUser() {
        User user = new UserImpl();
        int pageSize = 10;
        int pageNum = 1;
        List<Ticket> expectedTickets = Collections.singletonList(new TicketImpl());
        when(ticketService.getBookedTicketsByUser(user, pageSize, pageNum)).thenReturn(expectedTickets);

        List<Ticket> result = bookingFacade.getBookedTicketsByUser(user, pageSize, pageNum);

        assertEquals(expectedTickets, result);
    }

    @Test
    void testCancelTicket() {
        long ticketId = 1L;
        when(ticketService.cancelTicket(ticketId)).thenReturn(true);

        boolean result = bookingFacade.cancelTicket(ticketId);

        assertTrue(result);
    }

    @Test
    void testRefillUserAccount() {
        long userId = 1L;
        Long amount = 100L;

        doNothing().when(userAccountService).refillAccount(userId, amount);

        assertDoesNotThrow(() -> bookingFacade.refillUserAccount(userId, amount));
    }

    @Test
    void testReturnMoneyToUser() {
        UserAccount userAccount = new UserAccountImpl();
        when(userAccountService.updateUserAccount(userAccount)).thenReturn(true);

        boolean result = bookingFacade.returnMoneyToUser(userAccount);

        assertTrue(result);
    }

    @Test
    void testGetUserAccountById() {
        Long accountId = 1L;
        UserAccount expectedAccount = new UserAccountImpl();
        when(userAccountService.getUserAccountById(accountId)).thenReturn(expectedAccount);

        UserAccount result = bookingFacade.getUserAccountById(accountId);

        assertEquals(expectedAccount, result);
    }
}
