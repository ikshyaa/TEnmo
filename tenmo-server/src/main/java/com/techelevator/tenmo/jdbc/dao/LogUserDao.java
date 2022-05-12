package com.techelevator.tenmo.jdbc.dao;

import com.techelevator.tenmo.model.LogUser;

import java.util.List;

public interface LogUserDao {

    List<LogUser> findAll();

    LogUser findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password);
}
