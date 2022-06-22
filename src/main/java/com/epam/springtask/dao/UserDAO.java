package com.epam.springtask.dao;

import com.epam.springtask.model.User;
import com.epam.springtask.model.UserImplementation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface UserDAO extends JpaRepository<UserImplementation, Long> {
    User findByEmail(String email);
    List<User> findAllByName(String name);
}
