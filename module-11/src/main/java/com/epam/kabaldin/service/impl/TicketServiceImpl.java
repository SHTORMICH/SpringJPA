package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.TicketDAO;
import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.model.impl.TicketImpl;
import com.epam.kabaldin.service.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    private TicketDAO ticketDAO;

    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        Ticket ticket = new TicketImpl(userId, eventId, place, category);
        ticketDAO.saveTicket(ticket);
        return ticket;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketDAO.getTicketsByUserId(user.getId(), pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketDAO.getTicketsByEventId(event.getId(), pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketDAO.cancelTicket(ticketId);
    }
}

