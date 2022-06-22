package com.epam.springtask.configuration;

import com.epam.springtask.model.Event;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorHandle {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleErrors(Exception ex){
        ModelAndView thymeleaf = new ModelAndView("error");
        thymeleaf.addObject("error", "An error occurred");
        thymeleaf.addObject("message", ex.getMessage());
        thymeleaf.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return thymeleaf;
    }
}
