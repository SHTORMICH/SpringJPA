package com.epam.kabaldin.service;

import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.User;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    Optional<Ticket> getTicket(Long ticketId);
    public Ticket bookTicket(Ticket ticket);
    public List<Ticket> getBookedTicketsByUser(Optional<User> user, int pageSize, int pageNum);
    public List<Ticket> getBookedTicketsByEvent(Optional<Event> event, int pageSize, int pageNum);
    public boolean cancelTicket(long ticketId);
}
