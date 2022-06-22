package com.epam.springtask.dao;

import com.epam.springtask.model.EventImplementation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDao extends JpaRepository<EventImplementation, Long> {

}
