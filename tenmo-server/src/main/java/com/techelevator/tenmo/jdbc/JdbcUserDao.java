package com.techelevator.tenmo.jdbc;

import com.techelevator.tenmo.jdbc.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserDao implements UserDao {

    private static final BigDecimal STARTING_BALANCE = new BigDecimal("1000.00");
    private static List<User> users = new ArrayList<>();

    @Autowired
    JdbcTemplate jdbcTemplate;


    private static BigDecimal balance;


    @Override
    public List<User> getUsers()
    {

        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id " +
                ", username " +
                "FROM tenmo_user;";

        SqlRowSet userResults = jdbcTemplate.queryForRowSet(sql);
        while (userResults.next()) {
            User user = mapRowToUser(userResults);
            users.add(user);
        }
        return users;

    }

    private User mapRowToUser(SqlRowSet rowset) {
        User user = new User();
        user.setId(rowset.getLong("user_id"));
        user.setUsername(rowset.getString("username"));
        return user;

    }

    @Override
    public BigDecimal getBalance(String username) {

        String sql = "SELECT balance\n" +
                "FROM account as a\n" +
                "INNER JOIN tenmo_user as tu\n" +
                "ON a.user_id = tu.user_id\n" +
                "WHERE tu.username = ?\n" +
                ";\n";

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, username);

        if(row.next())
        {
            balance = row.getBigDecimal("balance");
            return balance;
        }
        return BigDecimal.ZERO;
    }

    @Override
    public String getUserName(int accountId)
    {
        String userName = "";

        String sql = "Select username " +
                "FROM tenmo_user as tu " +
                "Inner Join account as a " +
                "ON tu.user_id = a.user_id " +
                "where a.account_id = ? ;";

        userName = jdbcTemplate.queryForObject(sql, String.class, accountId);


        return userName;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public User getUser(int id)
    {
        User user = new User();
        String sql = "SELECT user_id " +
                     ",username " +
                     "FROM tenmo_user " +
                     "WHERE user_id = ?;";

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, id);

        if(row.next())
        {
            user.setUsername(row.getString("username"));
            user.setId(row.getLong("user_id"));
        }
        return user;
    }
}
