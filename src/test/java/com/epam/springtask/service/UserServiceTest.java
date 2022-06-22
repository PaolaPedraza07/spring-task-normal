package com.epam.springtask.service;

import com.epam.springtask.dao.UserDAO;
import com.epam.springtask.model.User;
import com.epam.springtask.model.UserImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserDAO userDAO;

    @Test
    public void getUserByIdSuccessPath(){
        UserImplementation userMock = new UserImplementation("Paola", "paola@gmail.com");
        Optional<UserImplementation> userOptionalMockResponse = Optional.of(userMock);
        Mockito.when(
                userDAO.findById(Mockito.any(Long.class))
        ).thenReturn(userOptionalMockResponse);

        User userResponse = userService.getUsertById(5);
        Assertions.assertNotNull(userResponse);
        Assertions.assertEquals("Paola", userResponse.getName());
    }
    @Test
    public void getUserByEmailSuccessPath(){
        UserImplementation userMock = new UserImplementation("Paola", "paola@gmail.com");
        UserImplementation userOptionalMockResponse = userMock;
        Mockito.when(
                userDAO.findByEmail(Mockito.any(String.class))
        ).thenReturn(userOptionalMockResponse);

        User userResponse = userService.getUserByEmail("paola@gmail.com");
        Assertions.assertNotNull(userResponse);
        Assertions.assertEquals("paola@gmail.com", userResponse.getEmail());
    }
}
