package com.epam.springtask.model;

import org.springframework.stereotype.Component;
import javax.persistence.*;

@Component
@Entity
@Table(name = "user")
public class UserImplementation implements User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @Column
    private String name;
    @Column
    private String email;

    public UserImplementation(String name, String email){
        this.name = name;
        this.email = email;
    }

    public UserImplementation(){

    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
