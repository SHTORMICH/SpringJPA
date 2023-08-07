package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.TicketDAO;
import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.model.impl.EventImpl;
import com.epam.kabaldin.model.impl.UserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TicketServiceImplTest {
    @Mock
    private TicketDAO ticketDAO;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBookTicket() {
        long userId = 1L;
        long eventId = 1L;
        int place = 5;
        Ticket.Category category = Ticket.Category.STANDARD;

        Ticket ticket = ticketService.bookTicket(userId, eventId, place, category);

        verify(ticketDAO, times(1)).saveTicket(ticket);

        assertEquals(userId, ticket.getUserId());
        assertEquals(eventId, ticket.getEventId());
        assertEquals(place, ticket.getPlace());
        assertEquals(category, ticket.getCategory());
    }

    @Test
    void testGetBookedTicketsByUser() {
        long userId = 1L;
        int pageSize = 10;
        int pageNum = 1;
        User user = new UserImpl();
        user.setId(userId);

        List<Ticket> tickets = ticketService.getBookedTickets(user, pageSize, pageNum);

        verify(ticketDAO, times(1)).getTicketsByUserId(userId, pageSize, pageNum);

    }

    @Test
    void testGetBookedTicketsByEvent() {
        long eventId = 1L;
        int pageSize = 10;
        int pageNum = 1;
        Event event = new EventImpl();
        event.setId(eventId);

        List<Ticket> tickets = ticketService.getBookedTickets(event, pageSize, pageNum);

        verify(ticketDAO, times(1)).getTicketsByEventId(eventId, pageSize, pageNum);
    }

    @Test
    void testCancelTicket() {
        long ticketId = 42L;

        when(ticketDAO.cancelTicket(ticketId)).thenReturn(true);

        boolean result = ticketService.cancelTicket(ticketId);

        verify(ticketDAO, times(1)).cancelTicket(ticketId);

        assertEquals(true, result);
    }

}
