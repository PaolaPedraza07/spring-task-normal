package com.epam.springtask.service;

import com.epam.springtask.dao.UserAccountDao;
import com.epam.springtask.dao.UserDAO;
import com.epam.springtask.model.User;
import com.epam.springtask.model.UserAccount;
import com.epam.springtask.model.UserImplementation;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserDAO userDAO;
    UserAccountDao userAccountDao;

    public UserService(UserDAO userDAO, UserAccountDao userAccountDao){
        this.userDAO = userDAO;
        this.userAccountDao = userAccountDao;
    }

    public User getUsertById(long userId){
        User user = userDAO.findById(userId).get();
        return user;
    }

    public User getUserByEmail(String email){
        User user = userDAO.findByEmail(email);
        return user;
    }

    public List<User> getUsersByName(String name){
        List<User> userList = userDAO.findAllByName(name);
        return userList;
    }

    public User createUser(User user) {
        return userDAO.save((UserImplementation)user);
    }

    public User updateUser(User user) {
        return userDAO.save((UserImplementation) user);
    }

    public boolean deleteUser(long eventId) {
        try {
            userDAO.deleteById(eventId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public UserAccount refillAccountByUserId(long userId, double amount){
        Optional<UserAccount> userAccount = userAccountDao.findById(userId);
        if(userAccount.isPresent()){
            double currentAmount = userAccount.get().getAmount()+amount;
            userAccount.get().setAmount(currentAmount);
            return userAccountDao.save(userAccount.get());
        }else {
            UserAccount newUserAccount = new UserAccount(userId, amount);
            return userAccountDao.save(newUserAccount);
        }
    }
}
