package com.epam.kabaldin.controller;

import com.epam.kabaldin.facade.BookingFacade;
import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingFacade bookingFacade;

    @Autowired
    public BookingController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @GetMapping("/event/{eventId}")
    public ModelAndView getEventById(@PathVariable long eventId) {

        Event event = bookingFacade.getEventById(eventId);
        ModelAndView modelAndView = new ModelAndView("event");
        modelAndView.addObject("event", event);
        return modelAndView;
    }

    @GetMapping("/events")
    public ModelAndView getEventsByTitle(@RequestParam("title") String title,
                                         @RequestParam int pageSize,
                                         @RequestParam int pageNum) {
        List<Event> events = bookingFacade.getEventsByTitle(title, pageSize, pageNum);
        ModelAndView modelAndView = new ModelAndView("events");
        modelAndView.addObject("events", events);
        return modelAndView;
    }

    @GetMapping("/events/day")
    public ModelAndView getEventsForDay(@RequestParam Date day,
                                        @RequestParam int pageSize,
                                        @RequestParam int pageNum) {
        List<Event> events = bookingFacade.getEventsForDay(day, pageSize, pageNum);
        ModelAndView modelAndView = new ModelAndView("events");
        modelAndView.addObject("events", events);
        return modelAndView;
    }

    @PostMapping("/event/create")
    public Event createEvent(@RequestBody Event event) {
        return bookingFacade.createEvent(event);
    }

    @PutMapping("/event")
    public Event updateEvent(@RequestBody Event event) {
        return bookingFacade.updateEvent(event);
    }

    @DeleteMapping("/event/{eventId}")
    public boolean deleteEvent(@PathVariable long eventId) {
        return bookingFacade.deleteEvent(eventId);
    }

    @GetMapping("/user/{userId}")
    public ModelAndView getUserById(@PathVariable long userId) {
        User user = bookingFacade.getUserById(userId);
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView getUsersByName(@RequestParam String name,
                                       @RequestParam int pageSize,
                                       @RequestParam int pageNum) {
        List<User> users = bookingFacade.getUsersByName(name, pageSize, pageNum);
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }


    @PostMapping("/user/create")
    public User createUser(@RequestBody User user) {
        return bookingFacade.createUser(user);
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        return bookingFacade.updateUser(user);
    }

    @DeleteMapping("/user/{userId}")
    public boolean deleteUser(@PathVariable long userId) {
        return bookingFacade.deleteUser(userId);
    }

    @PostMapping("/ticket")
    public Ticket bookTicket(@RequestParam Long userId,
                             @RequestParam Long eventId,
                             @RequestBody Ticket ticket) {
        Ticket bookedTicket = bookingFacade.bookTicket(userId, eventId, ticket.getPlace(), ticket.getCategory());
        BigDecimal ticketPrice = bookingFacade.getEventById(eventId).getTicketPrice();
        bookingFacade.refillUserAccount(userId, ticketPrice);
        return bookedTicket;
    }

    @GetMapping("/tickets")
    public ModelAndView getBookedTickets(@RequestParam long userId,
                                         @RequestParam int pageSize,
                                         @RequestParam int pageNum) {
        User user = bookingFacade.getUserById(userId);
        List<Ticket> tickets = bookingFacade.getBookedTicketsByUser(user, pageSize, pageNum);
        ModelAndView modelAndView = new ModelAndView("tickets");
        modelAndView.addObject("tickets", tickets);
        return modelAndView;
    }

    @GetMapping("/ticket/event/{eventId}")
    public ModelAndView getBookedTicketsByEvent(@PathVariable long eventId,
                                                @RequestParam int pageSize,
                                                @RequestParam int pageNum) {
        Event event = bookingFacade.getEventById(eventId);
        List<Ticket> tickets = bookingFacade.getBookedTicketsByEvent(event, pageSize, pageNum);
        ModelAndView modelAndView = new ModelAndView("tickets");
        modelAndView.addObject("tickets", tickets);
        modelAndView.addObject("tickets", tickets);
        return modelAndView;
    }

    @PostMapping("/ticket/{ticketId}")
    public boolean cancelTicket(@PathVariable long ticketId) {
        return bookingFacade.cancelTicket(ticketId);
    }

}
