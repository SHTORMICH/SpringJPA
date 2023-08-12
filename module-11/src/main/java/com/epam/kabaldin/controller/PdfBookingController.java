package com.epam.kabaldin.controller;

import com.epam.kabaldin.facade.BookingFacade;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/pdf")
public class PdfBookingController {

    private final BookingFacade bookingFacade;

    @Autowired
    public PdfBookingController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @GetMapping("/tickets")
    public void getPdfBookedTickets(@RequestParam long userId,
                                    @RequestParam int pageSize,
                                    @RequestParam int pageNum,
                                    HttpServletResponse response) throws IOException {
        User user = bookingFacade.getUserById(userId);
        List<Ticket> bookedTickets = bookingFacade.getBookedTicketsByUser(user, pageSize, pageNum);

        byte[] pdfData = generatePdf(bookedTickets);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"booked_tickets.pdf\"");
        response.setContentLength(pdfData.length);
        response.getOutputStream().write(pdfData);
        response.getOutputStream().flush();
    }

    private byte[] generatePdf(List<Ticket> bookedTickets) {
        try {
            Document document = new Document();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, byteArrayOutputStream);

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            for (Ticket ticket : bookedTickets) {
                Paragraph paragraph = new Paragraph("Ticket ID: " + ticket.getId(), font);
                document.add(paragraph);

                paragraph = new Paragraph("Event ID: " + ticket.getEvent(), font);
                document.add(paragraph);

                paragraph = new Paragraph("User ID: " + ticket.getUser(), font);
                document.add(paragraph);

                paragraph = new Paragraph("Category: " + ticket.getCategory().toString(), font);
                document.add(paragraph);

                paragraph = new Paragraph("Place: " + ticket.getPlace(), font);
                document.add(paragraph);

                document.add(new Paragraph(""));
            }

            document.close();

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private byte[] generatePdfData(List<Ticket> bookedTickets) {
        return generatePdf(bookedTickets);
    }
}

