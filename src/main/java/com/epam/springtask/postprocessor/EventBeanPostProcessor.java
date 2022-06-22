package com.epam.springtask.postprocessor;

import com.epam.springtask.model.Event;
import com.epam.springtask.model.EventImplementation;
import com.epam.springtask.storage.AppStorage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class EventBeanPostProcessor implements BeanPostProcessor {
    /**
     * Método que se ejecuta después de inicializar el bean
     */

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof AppStorage) {
            try{
                //Events
                AppStorage storage = (AppStorage) bean;
                Event event;
                FileReader readerEvent = new FileReader("src/main/resources/templates/events.json");
                Object obEvent = new JSONParser().parse(readerEvent);
                JSONObject jsEvent = (JSONObject) obEvent;
                JSONArray arrayEvent = (JSONArray)jsEvent.get("events");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                for (int i = 0; i < arrayEvent.size(); i++ ){
                    event = new EventImplementation();
                    JSONObject obj = (JSONObject)arrayEvent.get(i);
                    event.setId(Long.parseLong(obj.get("id").toString()));
                    event.setDate(format.parse(obj.get("date").toString()));
                    event.setTitle(obj.get("title").toString());
                    storage.events.put(String.valueOf(event.getId()), event);
                }

            }catch(Exception e){
                System.out.println("The Application Fails" + e);
            }
        }
        return bean;
    }
}
