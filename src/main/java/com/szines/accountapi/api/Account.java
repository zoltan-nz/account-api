package com.szines.accountapi.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Account {

    @NotNull
    private long id;

    @NotNull
    @Size(min = 2, max = 64)
    private String name;

    @NotNull
    private double balance;

    public Account(long id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
}
