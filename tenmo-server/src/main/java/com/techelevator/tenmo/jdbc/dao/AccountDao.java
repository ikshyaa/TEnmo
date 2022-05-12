package com.techelevator.tenmo.jdbc.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;

public interface AccountDao {

    public Account getAccount(int accountId);

    public boolean transfer(Transfer transfer);

    public boolean withdraw(Transfer transfer);

    public boolean deposit(Transfer transfer);

    public Integer getAccountId(int userId);

    public int getAccountIdByUsername(String username);
}
