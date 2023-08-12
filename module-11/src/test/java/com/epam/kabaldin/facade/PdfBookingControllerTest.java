package com.epam.kabaldin.facade;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.epam.kabaldin.controller.PdfBookingController;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.model.impl.TicketImpl;
import com.epam.kabaldin.model.impl.UserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.util.Collections;

class PdfBookingControllerTest {

    @Mock
    private BookingFacade bookingFacade;

    private PdfBookingController pdfBookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pdfBookingController = new PdfBookingController(bookingFacade);
    }

    @Test
    void testGetPdfBookedTickets() throws IOException {
        long userId = 1L;
        int pageSize = 10;
        int pageNum = 1;

        User user = new UserImpl();
        when(bookingFacade.getUserById(userId)).thenReturn(user);

        Ticket ticket = new TicketImpl();
        ticket.setId(1L);
        ticket.setEvent(null); // Set your event object
        ticket.setUser(user);
        ticket.setCategory(Ticket.Category.PREMIUM);
        ticket.setPlace(5);

        when(bookingFacade.getBookedTicketsByUser(user, pageSize, pageNum)).thenReturn(Collections.singletonList(ticket));

        MockHttpServletResponse response = new MockHttpServletResponse();
        pdfBookingController.getPdfBookedTickets(userId, pageSize, pageNum, response);

        assertEquals("application/pdf", response.getContentType());
        assertEquals("attachment; filename=\"booked_tickets.pdf\"", response.getHeader("Content-Disposition"));
    }
}
