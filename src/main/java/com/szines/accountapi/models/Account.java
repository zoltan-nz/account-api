package com.szines.accountapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

    @JsonProperty
    @Id
    private int id;

    @JsonProperty
    private String name;

    @JsonProperty
    private double balance;

    private Account() {
    }

//    public Account(int id, String name, double balance) {
//        this.id = id;
//        this.name = name;
//        this.balance = balance;
//    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
