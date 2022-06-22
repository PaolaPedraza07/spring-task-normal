package com.epam.springtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringTaskApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringTaskApplication.class, args);

		/*Logger logger = LoggerFactory.getLogger(SpringTaskApplication.class);
		logger.info("The program is running");
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"config/aplicationContext.xml");

		BookingFacadeImpl bookingFacadeImpl = appContext.getBean("bookingFacadeImpl", BookingFacadeImpl.class);

		Event event = bookingFacadeImpl.getEventById(2);
		if(event != null){
			logger.info("The event exists");
			System.out.println(event.getId());
			System.out.println(event.getTitle());
			System.out.println(event.getDate());
		}else {
			logger.error("The event doesn't exist");;
		}

		System.out.println("**********");

		User user = new UserImplementation();
		user.setId(1);
		user.setName("Paolaa");
		user.setEmail("ppppppp@email.com");
		List<Ticket> ticket = bookingFacadeImpl.getBookedTickets(user,1,1);
		if(ticket != null){
			for (Ticket t:ticket){
				logger.info("The ticket exists");
				System.out.println(t.getId());
				System.out.println(t.getEventId());
				System.out.println(t.getCategory());
				System.out.println(t.getPlace());
				System.out.println(t.getUserId());
			}
		}else {
			logger.error("The ticket doesn't exist");
		}

		System.out.println("**********");
		System.out.println("User by id");
		user  = bookingFacadeImpl.getUserById(2);
		System.out.println(user.getId());
		System.out.println(user.getName());


		System.out.println("*************");
		System.out.println("Users by name");
		List<User> userList = bookingFacadeImpl.getUsersByName("Paola", 1,1);
		for(int i=0; i<userList.size(); i++){
			User user1 = userList.get(i);
			System.out.println(user1.getId());
			System.out.println(user1.getName());
		}*/
	}

}
