package com.epam.springtask.dao;

import com.epam.springtask.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountDao extends JpaRepository<UserAccount, Long> {

}
