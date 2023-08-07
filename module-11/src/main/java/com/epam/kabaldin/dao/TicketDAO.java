package com.epam.kabaldin.dao;

import com.epam.kabaldin.model.Ticket;

import java.util.List;

public interface TicketDAO {
    void saveTicket(Ticket ticket);
    Ticket getTicketById(long ticketId);
    List<Ticket> getTicketsByUserId(long userId, int pageSize, int pageNum);
    List<Ticket> getTicketsByEventId(long eventId, int pageSize, int pageNum);
    Ticket updateTicket(Ticket ticket);
    boolean cancelTicket(long ticketId);
}

