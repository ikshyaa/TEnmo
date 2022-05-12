package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.jdbc.dao.LogUserDao;
import com.techelevator.tenmo.jdbc.dao.UserDao;
import com.techelevator.tenmo.model.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;


@RestController
public class UserController extends BaseController
{


    @Autowired
    public UserController(LogUserDao logUserDao, UserDao userDAO)
    {
        super(logUserDao, userDAO);
    }

    @ApiOperation("Check your balance!")
    @GetMapping("/myBalance")
    public BigDecimal getMyBalance(@ApiParam("Logged in user") Principal currentUser) {
        return userDao.getBalance(currentUser.getName());
    }
//
//    @ApiOperation("Check balance")
//    @GetMapping("/balance")
//    public BigDecimal getBalanceByUserName(User user)
//    {
//        return userDao.getBalance(user.getUsername());
//    }


    @ApiOperation("Gets all users")
    @GetMapping("/users")
    public List<User> getAllUser() {
        return userDao.getUsers();
    }

    @ApiOperation("Display UserId")
    @GetMapping("/users/{userName}")
    public int getId(@PathVariable @ApiParam("userName") String userName)
    {
        return logUserDao.findIdByUsername(userName);
    }

    @ApiOperation("Get User")
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable @ApiParam("userID") int id)
    {
        return userDao.getUser(id);

    }

}
