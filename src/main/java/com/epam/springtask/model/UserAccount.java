package com.epam.springtask.model;

import org.springframework.stereotype.Component;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "useraccount")
public class UserAccount {
    @Id
    @Column
    private long userId;
    @Column
    private double amount;

    public UserAccount(long userId, double amount){
        this.amount = amount;
        this.userId = userId;
    }

    public UserAccount(){

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
