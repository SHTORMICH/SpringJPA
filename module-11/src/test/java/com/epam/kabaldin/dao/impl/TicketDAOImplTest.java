package com.epam.kabaldin.dao.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.kabaldin.model.Entity;
import com.epam.kabaldin.model.Ticket.Category;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.impl.TicketImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketDAOImplTest {
    private final Map<String, List<? extends Entity>> storage = new HashMap<>();
    private List<Ticket> ticketStorage;
    private TicketDAOImpl ticketDAO;

    @BeforeEach
    public void setUp() {
        ticketDAO = new TicketDAOImpl();
        ticketStorage = new ArrayList<>();
        Ticket ticket = new TicketImpl();
        ticket.setId(1L);
        ticket.setEventId(1L);
        ticket.setPlace(1);
        ticket.setCategory(Category.STANDARD);

        ticketStorage.add(ticket);
        storage.put("ticket", ticketStorage);
        ticketDAO.setStorage(storage);
        ticketDAO.init();
    }

    @Test
    public void saveTicket_ShouldAddTicketToStorage() {
        Ticket ticket = new TicketImpl();
        ticket.setId(2L);
        ticket.setEventId(1L);
        ticket.setPlace(2);
        ticket.setCategory(Category.PREMIUM);

        ticketDAO.saveTicket(ticket);

        assertEquals(ticket, ticketDAO.getTicketById(2L));
    }

    @Test
    public void getTicketById_ShouldReturnCorrectTicket() {
        Ticket ticket = new TicketImpl();
        ticket.setId(1L);
        ticket.setEventId(1L);
        ticket.setPlace(1);
        ticket.setCategory(Category.PREMIUM);
        ticketDAO.saveTicket(ticket);

        Ticket retrievedTicket = ticketDAO.getTicketById(1L);

        assertEquals(ticket, retrievedTicket);
    }

    @Test
    public void getTicketsByUserId_ShouldReturnMatchingTickets() {
        Ticket ticket2 = new TicketImpl();
        ticket2.setId(2L);
        ticket2.setEventId(1L);
        ticket2.setPlace(2);
        ticket2.setCategory(Category.PREMIUM);
        ticketDAO.saveTicket(ticket2);

        List<Ticket> tickets = ticketDAO.getTicketsByUserId(1L, 10, 1);

        assertTrue(tickets.contains(ticketDAO.getTicketById(1L)));
        assertFalse(tickets.contains(ticket2));
    }

    @Test
    public void getTicketsByEventId_ShouldReturnMatchingTickets() {
        Ticket ticket2 = new TicketImpl(2L, 1L, 2, Category.PREMIUM);
        ticketDAO.saveTicket(ticket2);

        List<Ticket> tickets = ticketDAO.getTicketsByEventId(1L, 10, 1);

        assertEquals(2, tickets.size());
        assertTrue(tickets.contains(ticket2));
    }

    @Test
    public void updateTicket_ShouldUpdateExistingTicket() {
        Ticket ticket = new TicketImpl(2L, 1L, 1, Category.PREMIUM);
        ticketDAO.saveTicket(ticket);

        ticket.setCategory(Category.BAR);

        Ticket updatedTicket = ticketDAO.updateTicket(ticket);

        assertEquals(ticket.getCategory(), updatedTicket.getCategory());
        assertEquals(Category.BAR, updatedTicket.getCategory());
    }

    @Test
    public void deleteTicket_ShouldRemoveTicketFromStorage() {
        boolean isDeleted = ticketDAO.deleteTicket(1L);

        assertTrue(isDeleted);
    }
}
