package com.epam.springtask.facade;

import com.epam.springtask.model.User;
import com.epam.springtask.model.UserImplementation;
import com.epam.springtask.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"config/aplicationContext.xml"})
class BookingFacadeImplTest {
    @Autowired
    ApplicationContext ctx;

    @Test
    public void getValidEventById(){
        BookingFacadeImpl bookingFacadeImpl = ctx.getBean("bookingFacadeImpl", BookingFacadeImpl.class);
        bookingFacadeImpl.getEventById(2);
        assertTrue(true);
    }
}