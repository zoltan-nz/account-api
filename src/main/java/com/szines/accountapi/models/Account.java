package com.szines.accountapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

    private int id;
    private String name;
    private double balance;

    private Account() {

    }

    public Account(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    @JsonProperty
    @Id
    public int getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public double getBalance() {
        return balance;
    }

    @JsonProperty
    public void setId(int id) {
        this.id = id;
    }
    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
