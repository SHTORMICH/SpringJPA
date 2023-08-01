package com.epam.kabaldin.service;

import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.User;

import java.util.List;

public interface TicketService {
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);
    public boolean cancelTicket(long ticketId);
}
