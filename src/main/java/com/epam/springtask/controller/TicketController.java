package com.epam.springtask.controller;

import com.epam.springtask.SpringTaskApplication;
import com.epam.springtask.facade.BookingFacadeImpl;
import com.epam.springtask.model.Ticket;
import com.epam.springtask.model.TicketImplementation;
import com.epam.springtask.model.User;
import com.epam.springtask.model.UserImplementation;
import com.epam.springtask.utilities.CreatorPDF;
import com.epam.springtask.utilities.TicketsLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    BookingFacadeImpl bookingFacadeImpl;
    @Autowired
    CreatorPDF creatorPDF;
    @Autowired
    TicketsLoader ticketsLoader;

    Logger logger = LoggerFactory.getLogger(SpringTaskApplication.class);

    /**
     * This is a controller to book a ticket
     *
     * @param userId
     * @param eventId
     * @param place
     * @param category
     * @return ticket
     */
    @PostMapping("/book")
    public Ticket bookTicket(@RequestParam long userId, @RequestParam long eventId, @RequestParam int place, @RequestParam Ticket.Category category){
        Ticket ticket = bookingFacadeImpl.bookTicket(userId, eventId, place, category);
        return ticket;
    }

    @DeleteMapping("delete/{ticketId}")
    public String cancelTicket(@PathVariable Long ticketId){
        boolean deleted = bookingFacadeImpl.cancelTicket(ticketId);
        if(deleted){
            return "The ticket "+ticketId+" was deleted successfully";
        }
        return "The ticket "+ticketId+" doesn't exist";
    }

    @GetMapping(value = "booking", headers = "Accept=application/pdf", produces = "application/pdf")
    public ResponseEntity<InputStreamResource> getBookedTicketsByUser(@RequestParam Long userId) {
        logger.info("This method return a specific ticket by a specific user and create a pdf file with the result");

        User user = new UserImplementation();
        user.setId(userId);
        List<Ticket> bookedTickets = bookingFacadeImpl.getBookedTickets(user, 1,2);

        ByteArrayInputStream pdfInBytes = creatorPDF.ticketReportPDF(bookedTickets);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=ticketsReport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfInBytes));
    }

    @GetMapping("preload")
    public String preloadTickets(Model model) throws JAXBException {
        String path = "src/main/resources/files/tickets.xml";
            List <TicketImplementation> list = null;
            list = ticketsLoader.loadTickets(path);
            bookingFacadeImpl.bookListTicket(list);
            return "Tickets has been loaded successfully";
    }
}
