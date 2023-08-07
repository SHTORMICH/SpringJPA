package com.epam.kabaldin;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.kabaldin.facade.BookingFacade;
import com.epam.kabaldin.facade.BookingFacadeImpl;
import com.epam.kabaldin.model.Entity;
import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.model.impl.EventImpl;
import com.epam.kabaldin.model.impl.UserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingFacadeImplIntegrationTest {
    private final Map<String, List<? extends Entity>> storage = new HashMap<>();

    private BookingFacade bookingFacade;

    @BeforeEach
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
        bookingFacade = context.getBean(BookingFacadeImpl.class);
    }

    @Test
    public void testCreateUserAndEventBookAndCancelTicket() {
        User user = new UserImpl();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        User createdUser = bookingFacade.createUser(user);
        assertNotNull(createdUser);
        long userId = createdUser.getId();

        Event event = new EventImpl();
        event.setTitle("Sample Event");
        event.setDate(new Date());
        Event createdEvent = bookingFacade.createEvent(event);
        assertNotNull(createdEvent);
        long eventId = createdEvent.getId();

        int place = 1;
        Ticket.Category category = Ticket.Category.STANDARD;
        Ticket bookedTicket = bookingFacade.bookTicket(userId, eventId, place, category);
        assertNotNull(bookedTicket);
        long ticketId = bookedTicket.getId();

        assertEquals(userId, bookedTicket.getUserId());
        assertEquals(eventId, bookedTicket.getEventId());
        assertEquals(place, bookedTicket.getPlace());
        assertEquals(category, bookedTicket.getCategory());

        List<Ticket> bookedTicketsForUser = bookingFacade.getBookedTickets(createdUser, 10, 1);
        assertEquals(1, bookedTicketsForUser.size());

        List<Ticket> bookedTicketsForEvent = bookingFacade.getBookedTickets(createdEvent, 10, 1);
        assertEquals(1, bookedTicketsForEvent.size());

        boolean isCancelled = bookingFacade.cancelTicket(ticketId);
        assertTrue(isCancelled);

        List<Ticket> bookedTicketsAfterCancellation = bookingFacade.getBookedTickets(createdUser, 10, 1);
        assertEquals(0, bookedTicketsAfterCancellation.size());

        List<Ticket> bookedTicketsForEventAfterCancellation = bookingFacade.getBookedTickets(createdEvent, 10, 1);
        assertEquals(0, bookedTicketsForEventAfterCancellation.size());
    }
}
