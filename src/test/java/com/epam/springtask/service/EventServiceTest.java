package com.epam.springtask.service;

import com.epam.springtask.dao.EventDAO;
import com.epam.springtask.model.Event;
import com.epam.springtask.model.EventImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    @InjectMocks
    EventService eventService;
    @Mock
    EventDAO eventDAO;

    @Test
    public void getEventByIdSuccessPath(){
        EventImplementation eventMock = new EventImplementation("titleTest", new Date(), 500);
        Optional<EventImplementation> eventOptionalMockResponse = Optional.of(eventMock);
        Mockito.when(
                eventDAO.findById(Mockito.any(Long.class))
        ).thenReturn(eventOptionalMockResponse);

        Event eventResponse = eventService.getEventById(5);
        Assertions.assertNotNull(eventResponse);
        Assertions.assertEquals(500, eventResponse.getTicketprice());
        Assertions.assertEquals("titleTest", eventResponse.getTitle());
    }

    @Test
    public void getEventByTitleSuccessPath(){
        EventImplementation eventMock = new EventImplementation("titleTest", new Date(), 500);
        EventImplementation eventMock1 = new EventImplementation("titleTest1", new Date(), 600);
        List <Event> eventOptionalMockResponse = new ArrayList<>();
        eventOptionalMockResponse.add(eventMock);
        eventOptionalMockResponse.add(eventMock1);
        Mockito.when(
                eventDAO.findAllByTitle(Mockito.any(String.class))
        ).thenReturn(eventOptionalMockResponse);

        List<Event> eventList = eventService.getEventsByTitle("titleTest");
        Assertions.assertEquals("titleTest", eventList.get(0).getTitle());
        Assertions.assertEquals("titleTest1", eventList.get(1).getTitle());
        Assertions.assertEquals(2, eventList.size());
    }
}
