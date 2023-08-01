package com.epam.kabaldin.dao.impl;

import com.epam.kabaldin.dao.TicketDAO;
import com.epam.kabaldin.model.Entity;
import com.epam.kabaldin.model.Ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketDAOImpl implements TicketDAO {
    private List<Ticket> ticketStorage;
    private Map<String, List<? extends Entity>> storage;

    public void init() {
        ticketStorage = (List<Ticket>) storage.computeIfAbsent("ticket", k -> new ArrayList<>());
    }

    public void setStorage(Map<String, List<? extends Entity>> storage) {
        this.storage = storage;
    }

    @Override
    public void saveTicket(Ticket ticket) {
        ticketStorage.add(ticket);
    }

    @Override
    public Ticket getTicketById(long ticketId) {
        for (Ticket ticket : ticketStorage) {
            if (ticket.getId() == ticketId) {
                return ticket;
            }
        }
        return null;
    }

    @Override
    public List<Ticket> getTicketsByUserId(long userId, int pageSize, int pageNum) {
        List<Ticket> tickets = ticketStorage.stream()
                .filter(ticket -> ticket.getId() == userId)
                .collect(Collectors.toList());
        return getTicketsWithSuitableSizeAndNum(pageSize, pageNum, tickets);
    }

    @Override
    public List<Ticket> getTicketsByEventId(long eventId, int pageSize, int pageNum) {
        List<Ticket> tickets = ticketStorage.stream()
                .filter(ticket -> ticket.getEventId() == eventId)
                .collect(Collectors.toList());
        return getTicketsWithSuitableSizeAndNum(pageSize, pageNum, tickets);
    }

    private static List<Ticket> getTicketsWithSuitableSizeAndNum(int pageSize, int pageNum, List<Ticket> tickets) {
        int startIndex = (pageNum - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, tickets.size());

        if (startIndex >= tickets.size()) {
            return Collections.emptyList();
        }
        return tickets.subList(startIndex, endIndex);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        for (Ticket ticketForUpdate : ticketStorage) {
            if (ticketForUpdate.getId() == ticket.getId()) {
                ticketForUpdate.setUserId(ticket.getUserId());
                ticketForUpdate.setEventId(ticket.getEventId());
                ticketForUpdate.setPlace(ticket.getPlace());
                ticketForUpdate.setCategory(ticket.getCategory());
                return ticketForUpdate;
            }
        }
        return null;
    }

    @Override
    public boolean deleteTicket(long ticketId) {
        for (Ticket ticketForDelete : ticketStorage) {
            if (ticketForDelete.getId() == ticketId) {
                ticketStorage.remove(ticketForDelete);
                return true;
            }
        }
        return false;
    }
}

