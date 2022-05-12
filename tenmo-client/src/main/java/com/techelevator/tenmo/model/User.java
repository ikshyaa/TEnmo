package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class User {
    // added cash register for balance
    private int userId;
    private String username;

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof User) {
            User otherUser = (User) other;
            return otherUser.getId()== userId
                    && otherUser.getUsername().equals(username);
        } else {
            return false;
        }
    }
}