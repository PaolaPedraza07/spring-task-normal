package com.epam.springtask;

import com.epam.springtask.facade.BookingFacade;
import com.epam.springtask.facade.BookingFacadeImpl;
import com.epam.springtask.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpringTaskApplicationTests {

	@Test
	void contextLoads() throws ParseException {
		Event event = new EventImplementation();
		Ticket ticket =  new TicketImplementation();
		User user= new UserImplementation();

		user.setId(8);
		user.setName("pao");
		user.setEmail("pao@email.com");

		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"config/aplicationContext.xml");
		BookingFacadeImpl bookingFacadeImpl = appContext.getBean("bookingFacadeImpl", BookingFacadeImpl.class);
		User newUser = bookingFacadeImpl.createUser(user);
		assertNotNull(newUser);

		event.setId(8);
		event.setTitle("title");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		event.setDate(format.parse("04/04/2020"));
		Event newEvent =  bookingFacadeImpl.createEvent(event);
		assertNotNull(newEvent);

		ticket.setId(8L);
		ticket.setCategory(Ticket.Category.BAR);
		ticket.setEventId(1);
		ticket.setPlace(1);
		ticket.setUserId(1);
		Ticket newTicket =  bookingFacadeImpl.bookTicket(newUser.getId(),newEvent.getId(), 1,  Ticket.Category.PREMIUM);
		assertNotNull(newTicket);
		boolean cancel = bookingFacadeImpl.cancelTicket(newTicket.getId());
		assertTrue(cancel);

	}
}
