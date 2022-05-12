package com.techelevator.tenmo.jdbc.dao;

import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserDao {

    public List<User> getUsers();

    public BigDecimal getBalance(String userName);

    public String getUserName(int id);

    public long getId();

    public User getUser(int id);

}
