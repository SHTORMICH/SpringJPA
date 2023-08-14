package com.epam.kabaldin.facade;

import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.model.UserAccount;
import com.epam.kabaldin.model.impl.EventImpl;
import com.epam.kabaldin.model.impl.TicketImpl;
import com.epam.kabaldin.model.impl.UserImpl;
import com.epam.kabaldin.service.EventService;
import com.epam.kabaldin.service.TicketService;
import com.epam.kabaldin.service.UserAccountService;
import com.epam.kabaldin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class BookingFacadeImpl implements BookingFacade {
    private final UserService userService;
    private final EventService eventService;
    private final TicketService ticketService;
    private final UserAccountService userAccountService;

    @Autowired
    public BookingFacadeImpl(UserService userService, EventService eventService, TicketService ticketService, UserAccountService userAccountService) {
        this.userService = userService;
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.userAccountService = userAccountService;
    }

    @Override
    public Event getEventById(long eventId) {
        return eventService.getEventById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventService.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventService.getEventsForDay(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        return eventService.createEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventService.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventService.deleteEvent(eventId);
    }

    @Override
    public User getUserById(long userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userService.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userService.deleteUser(userId);
    }

    @Override
    public Ticket getTicket(Long ticketId) {
        return ticketService.getTicket(ticketId);
    }

    @Override
    public Ticket bookTicket(Long userId, Long eventId, int place, Ticket.Category category) {
        User user = new UserImpl();
        user.setId(userId);
        Event event = new EventImpl();
        event.setId(eventId);
        Ticket ticket = new TicketImpl();
        ticket.setUser(user);
        ticket.setEvent(event);
        ticket.setPlace(place);
        ticket.setCategory(category);
        return ticketService.bookTicket(ticket);
    }

    @Override
    public List<Ticket> getBookedTicketsByUser(User user, int pageSize, int pageNum) {
        return ticketService.getBookedTicketsByUser(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTicketsByEvent(Event event, int pageSize, int pageNum) {
        return ticketService.getBookedTicketsByEvent(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketService.cancelTicket(ticketId);
    }

    @Override
    public void refillUserAccount(long userId, BigDecimal amount) {
        userAccountService.refillAccount(userId, amount);
    }

    @Override
    public boolean returnMoneyToUser(UserAccount userAccount) {
        return userAccountService.updateUserAccount(userAccount);
    }

    @Override
    public UserAccount getUserAccountById(Long accountId) {
        return userAccountService.getUserAccountById(accountId);
    }
}
