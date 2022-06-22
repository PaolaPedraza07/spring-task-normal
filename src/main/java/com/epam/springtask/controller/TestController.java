package com.epam.springtask.controller;

import com.epam.springtask.dao.TestDao;
import com.epam.springtask.model.EventImplementation;
import com.epam.springtask.model.Ticket;
import com.epam.springtask.model.TicketImplementation;
import com.epam.springtask.model.UserImplementation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    TestDao dao;

    @GetMapping("byId")
    public EventImplementation getEventId(){
        EventImplementation e =dao.findById(2L).get();
        return e;
    }

    @GetMapping("saveEvent")
    public String testGuardarEvent(){
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(EventImplementation.class).buildSessionFactory();
            Session session = sessionFactory.openSession();
            EventImplementation event = new EventImplementation("event1", new Date(), 5767);
            session.beginTransaction();
            session.save(event);
            session.getTransaction().commit();
            session.close();
            return "Se guardó correctamente";
        }catch (Exception e){
            System.out.println("ERROR: "+e);
            return "No se guardo el evento";
        }
    }
    @GetMapping("saveTicket")
    public String testGuardarTicket(){
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(TicketImplementation.class).buildSessionFactory();
            Session session = sessionFactory.openSession();
            TicketImplementation ticket = new TicketImplementation(22222, 11111, Ticket.Category.STANDARD, 1);
            session.beginTransaction();
            session.save(ticket);
            session.getTransaction().commit();
            session.close();
            return "El ticket se guardó correctamente";
        }catch (Exception e){
            System.out.println("ERROR: "+e);
            return "No se guardo el ticket";
        }
    }
    @GetMapping("saveUser")
    public String testGuardarUser(){
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserImplementation.class).buildSessionFactory();
            Session session = sessionFactory.openSession();
            UserImplementation user1 = new UserImplementation("Paola", "paola@gmail.com");
            session.beginTransaction();
            session.save(user1);
            session.getTransaction().commit();
            session.close();
            return "El usuario se guardó correctamente";
        }catch (Exception e){
            System.out.println("ERROR: "+e);
            return "No se guardo el usuario";
        }
    }
}
