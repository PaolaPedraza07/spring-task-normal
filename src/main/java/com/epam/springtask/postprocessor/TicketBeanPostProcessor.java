package com.epam.springtask.postprocessor;

import com.epam.springtask.model.*;
import com.epam.springtask.storage.AppStorage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.io.FileReader;

@Component
public class TicketBeanPostProcessor implements BeanPostProcessor {
    /**
     * Método que se ejecuta después de inicializar el bean
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof AppStorage) {
            AppStorage storage = (AppStorage) bean;
            try{
                //Ticket
                FileReader readerTicket = new FileReader("src/main/resources/templates/ticket.json");
                Object obTicket = new JSONParser().parse(readerTicket);
                JSONObject jsTicket = (JSONObject) obTicket;
                JSONArray arrayTicket = (JSONArray)jsTicket.get("tickets");
                Ticket ticket;
                for (int i = 0; i < arrayTicket.size(); i++ ){
                    ticket = new TicketImplementation();
                    JSONObject obj = (JSONObject)arrayTicket.get(i);
                    ticket.setId(Long.parseLong(obj.get("id").toString()));
                    ticket.setUserId(Long.parseLong(obj.get("eventId").toString()));
                    ticket.setEventId(Long.parseLong(obj.get("userId").toString()));
                    ticket.setPlace(Integer.parseInt(obj.get("place").toString()));
                    if (obj.get("category").toString().equals(Ticket.Category.BAR.toString())){
                        ticket.setCategory(Ticket.Category.BAR);
                    }else if (obj.get("category").toString().equals(Ticket.Category.PREMIUM.toString())) {
                        ticket.setCategory(Ticket.Category.PREMIUM);
                    }else{
                        ticket.setCategory(Ticket.Category.STANDARD);
                    }
                    storage.tickets.put(String.valueOf(ticket.getId()), ticket);
                }

            }catch(Exception e){
                System.out.println("The Application Fails" + e);
            }
        }
        return bean;
    }
}
