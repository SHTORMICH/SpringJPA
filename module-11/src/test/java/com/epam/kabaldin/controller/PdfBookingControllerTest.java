package com.epam.kabaldin.controller;

import com.epam.kabaldin.facade.BookingFacade;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.model.impl.TicketImpl;
import com.epam.kabaldin.model.impl.UserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PdfBookingControllerTest {

    @Mock
    private BookingFacade bookingFacade;

    @InjectMocks
    private PdfBookingController pdfBookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPdfBookedTickets() throws IOException {
        long userId = 1;
        int pageSize = 10;
        int pageNum = 1;
        User user = new UserImpl();
        user.setId(userId);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        List<Ticket> bookedTickets = new ArrayList<>();
        Ticket ticket1 = new TicketImpl();
        ticket1.setUserId(1L);
        ticket1.setEventId(1);
        ticket1.setCategory(Ticket.Category.PREMIUM);
        ticket1.setPlace(10);
        Ticket ticket2 = new TicketImpl();
        ticket2.setUserId(2L);
        ticket2.setEventId(2);
        ticket2.setCategory(Ticket.Category.STANDARD);
        ticket2.setPlace(20);
        bookedTickets.add(ticket1);
        bookedTickets.add(ticket2);

        when(bookingFacade.getUserById(userId)).thenReturn(user);
        when(bookingFacade.getBookedTickets(user, pageSize, pageNum)).thenReturn(bookedTickets);

        MockHttpServletResponse response = new MockHttpServletResponse();
        pdfBookingController.getPdfBookedTickets(userId, pageSize, pageNum, response);

        assertEquals(MediaType.APPLICATION_PDF_VALUE, response.getContentType());
        assertEquals("attachment; filename=\"booked_tickets.pdf\"", response.getHeader(HttpHeaders.CONTENT_DISPOSITION));

        byte[] pdfData = response.getContentAsByteArray();
        assertTrue(pdfData.length > 0);
    }
}
