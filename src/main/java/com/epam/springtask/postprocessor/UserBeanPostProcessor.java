package com.epam.springtask.postprocessor;

import com.epam.springtask.model.User;
import com.epam.springtask.model.UserImplementation;
import com.epam.springtask.storage.AppStorage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.io.FileReader;

@Component
public class UserBeanPostProcessor implements BeanPostProcessor {
    /**
     * Método que se ejecuta después de inicializar el bean
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof AppStorage) {
            AppStorage storage = (AppStorage) bean;
            User user = new UserImplementation();

            try{
                //User
                FileReader readerUser = new FileReader("src/main/resources/templates/user.json");
                Object obUser = new JSONParser().parse(readerUser);
                JSONObject jsUser = (JSONObject) obUser;
                JSONArray arrayUser = (JSONArray)jsUser.get("users");

                for (int i = 0; i < arrayUser.size(); i++ ){
                    user = new UserImplementation();
                    JSONObject obj = (JSONObject)arrayUser.get(i);
                    user.setId(Long.parseLong(obj.get("id").toString()));
                    user.setName(obj.get("name").toString());
                    user.setEmail(obj.get("email").toString());
                    storage.users.put(String.valueOf(user.getId()), user);
                }
            }catch(Exception e){
                System.out.println("The Application Fails" + e);
            }
        }
        return bean;
    }
}
