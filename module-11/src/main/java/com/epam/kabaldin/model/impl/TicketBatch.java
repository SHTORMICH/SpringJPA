package com.epam.kabaldin.model.impl;

import com.epam.kabaldin.model.Ticket;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
public class TicketBatch {
    private List<Ticket> tickets;

    @XmlElement(name = "ticket")
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}

