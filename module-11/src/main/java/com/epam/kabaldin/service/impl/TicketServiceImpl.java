package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.TicketDAO;
import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.model.impl.TicketImpl;
import com.epam.kabaldin.service.TicketService;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class TicketServiceImpl implements TicketService {
    private TicketDAO ticketDAO;

    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Override
    public Ticket getTicket (Long ticketId) {
        Optional<TicketImpl> ticketOp = ticketDAO.findById(ticketId);
        ticketOp.isPresent();
        return ticketOp.get();
    }
    @Override
    public Ticket bookTicket(Ticket ticket) {
        ticketDAO.save((TicketImpl) ticket);
        return ticket;
    }

    @Override
    public List<Ticket> getBookedTicketsByUser(User user, int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return ticketDAO.findAllById(user.getId(),  pageable);
    }

    @Override
    public List<Ticket> getBookedTicketsByEvent(Event event, int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return ticketDAO.findAllById(event.getId(), pageable);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        Ticket ticket = getTicket(ticketId);
        ticket.setUser(null);
        ticketDAO.save((TicketImpl) ticket);
        return true;
    }
}

