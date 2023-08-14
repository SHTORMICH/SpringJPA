package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.TicketDAO;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketServiceImplTest {

    @Mock
    private TicketDAO ticketDAO;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTicket() {
        Long ticketId = 1L;
        Ticket ticket = new TicketImpl();
        when(ticketDAO.findById(ticketId)).thenReturn(Optional.of((TicketImpl) ticket));

        Ticket result = ticketService.getTicket(ticketId);

        assertNotNull(result);
        assertEquals(ticket, result);
    }

    @Test
    public void testBookTicket() {
        Ticket ticket = new TicketImpl();

        Ticket result = ticketService.bookTicket(ticket);

        assertNotNull(result);
        assertEquals(ticket, result);
        verify(ticketDAO, times(1)).save(ticket);
    }

    @Test
    public void testGetBookedTicketsByUser() {
        User user = new UserImpl();
        int pageSize = 10;
        int pageNum = 0;
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<Ticket> tickets = new ArrayList<>();
        when(ticketDAO.findAllById(user.getId(), pageable)).thenReturn(tickets);

        List<Ticket> result = ticketService.getBookedTicketsByUser(user, pageSize, pageNum);

        assertNotNull(result);
        assertEquals(tickets, result);
    }

    @Test
    public void testGetBookedTicketsByEvent() {
        Event event = new EventImpl();
        int pageSize = 10;
        int pageNum = 0;
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<Ticket> tickets = new ArrayList<>();
        when(ticketDAO.findAllById(event.getId(), pageable)).thenReturn(tickets);

        List<Ticket> result = ticketService.getBookedTicketsByEvent(event, pageSize, pageNum);

        assertNotNull(result);
        assertEquals(tickets, result);
    }

    @Test
    public void testCancelTicket() {
        long ticketId = 1L;
        Ticket ticket = new TicketImpl();
        when(ticketDAO.findById(ticketId)).thenReturn(Optional.of((TicketImpl) ticket));

        boolean result = ticketService.cancelTicket(ticketId);

        assertTrue(result);
        assertNull(ticket.getUser());
        verify(ticketDAO, times(1)).save(ticket);
    }
}
