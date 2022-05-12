package com.techelevator.tenmo.jdbc;

import com.techelevator.tenmo.jdbc.dao.AccountDao;
import com.techelevator.tenmo.jdbc.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;

@Component
public class JdbcAccountDao implements AccountDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    Principal principal;

    @Autowired
    UserDao userDao;

    @Override
    public Account getAccount(int accountId) {

        return null;
    }

    public boolean transfer(Transfer transfer)
    {
        boolean sent = withdraw(transfer);
        boolean received = deposit(transfer);

        return sent && received;
    }


    @Override
    public boolean withdraw(Transfer transfer) {

        BigDecimal balance = userDao.getBalance(transfer.getSenderName());
        BigDecimal newBalance = balance.subtract(transfer.getAmount());

        if(balance.compareTo(transfer.getAmount()) >0)
        {
            String sql = "Update account " +
                         "SET balance =  ? " +
                         "Where account_id = ? " +
                        "RETURNING user_id;";

            Integer userId = jdbcTemplate.queryForObject(sql,Integer.class, newBalance, transfer.getSenderAccountId());

            if (userId != null) return true;

        }
        return false;
    }

//    public void addBalance(int account_id, int money)
//    {
//        Account account = getAccount(account_id);
//        //////
//        BigDecimal newBalance = account.getBalance();
//
//    }


    @Override
    public boolean deposit(Transfer transfer) {


        String sql = "Update account " +
                "SET balance = balance + ? " +
                "Where account_id = ?" +
                " RETURNING user_id;";

        Integer user_id= jdbcTemplate.queryForObject(sql, Integer.class, transfer.getAmount(), transfer.getAccountToId());

        if(user_id != null) return true;
        return false;

    }

    @Override
    public Integer getAccountId(int userId)
    {
        String sql = "SELECT account_id FROM account WHERE user_id = ?;";

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, userId);

        if(row.next())
        {
            return row.getInt("account_id");
        }

        return 0;
    }

    @Override
    public int getAccountIdByUsername(String username)
    {
        String sql = "SELECT account_id \n" +
                "From account as a\n" +
                "inner join tenmo_user as tu\n" +
                "on a.user_id = tu.user_id\n" +
                "where tu.username = ?\n" +
                ";\n";

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, username);

        if(row.next())
        {
            return row.getInt("account_id");
        }

        return 0;
    }

}
