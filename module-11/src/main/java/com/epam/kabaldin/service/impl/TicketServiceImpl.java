package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.TicketDAO;
import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public class TicketServiceImpl implements TicketService {
    private TicketDAO ticketDAO;

    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Override
    public Optional<Ticket> getTicket (Long ticketId) {
        return ticketDAO.findById(ticketId);
    }
    @Override
    public Ticket bookTicket(Ticket ticket) {
        ticketDAO.save(ticket);
        return ticket;
    }

    @Override
    public List<Ticket> getBookedTicketsByUser(Optional<User> user, int pageSize, int pageNum) {
        Pageable pageable = (Pageable) PageRequest.of(pageNum, pageSize);
        return ticketDAO.findAllById(user.get().getId(),  pageable);
    }

    @Override
    public List<Ticket> getBookedTicketsByEvent(Optional<Event> event, int pageSize, int pageNum) {
        Pageable pageable = (Pageable) PageRequest.of(pageNum, pageSize);
        return ticketDAO.findAllById(event.get().getId(), pageable);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketDAO.cancelTicket(ticketId);
    }
}

