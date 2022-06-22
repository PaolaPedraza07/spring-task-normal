package com.epam.springtask.controller;

import com.epam.springtask.facade.BookingFacadeImpl;
import com.epam.springtask.model.User;
import com.epam.springtask.model.UserAccount;
import com.epam.springtask.model.UserImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    BookingFacadeImpl bookingFacade;

    /**
     *This is a controller to get an User by its id
     *
     * @param userId
     * @return ModelAndView
     */
    @GetMapping("/id/{userId}")
    public ModelAndView getUserById(@PathVariable Long userId){
        ModelAndView thymeleaf = new ModelAndView("user");
        User userResponse = bookingFacade.getUserById(userId);
        thymeleaf.addObject("user", userResponse);
        return thymeleaf;
    }

    /**
     * This is a controller to get an User by email
     *
     * @param userEmail
     * @return ModelAndView
     */
    @GetMapping("/email/{userEmail}")
    public ModelAndView getUserByEmail(@PathVariable String userEmail){
        ModelAndView thymeleaf = new ModelAndView("user");
        User userResponse = bookingFacade.getUserByEmail(userEmail);
        thymeleaf.addObject("user", userResponse);
        return thymeleaf;
    }

    /**
     * This is a controller to get a List of Users by name
     *
     * @param name
     * @return ModelAndView
     */
    @GetMapping("/name/{name}")
    public ModelAndView getUsersByName(@PathVariable String name){
        ModelAndView thymeleaf = new ModelAndView("userList");
        List<User> userList = bookingFacade.getUsersByName(name, 1,1);
        thymeleaf.addObject("userList", userList);
        return thymeleaf;
    }

    /**
     * This is a controller to create an User
     *
     * @param user
     * @return the User created
     */
    @PostMapping("/create")
    public User createUser(@RequestBody UserImplementation user){
        User userResponse = bookingFacade.createUser(user);
        return userResponse;
    }

    /**
     * This is a controller to update an User
     *
     * @param user
     * @return the User updated
     */
    @PutMapping("/update")
    public User updateUser(@RequestBody UserImplementation user){
        User userResponse = bookingFacade.updateUser(user);
        return userResponse;
    }


    /**
     * This is a controller to delete an User
     *
     * @param userId
     * @return String
     */
    @DeleteMapping("delete/{userId}")
    public String deleteUser(@PathVariable Long userId){
        boolean deleted = bookingFacade.deleteUser(userId);
        if(deleted){
            return "The user "+userId+" was deleted successfully";
        }
        return "The user "+userId+" doesn't exist";
    }

    @PostMapping("/refillAccount")
    public UserAccount refillAccountByUserId(@RequestBody UserAccount userAccount){
        UserAccount userAccountResponse = bookingFacade.refillAccount(userAccount.getUserId(), userAccount.getAmount());
        return userAccountResponse;
    }
}
