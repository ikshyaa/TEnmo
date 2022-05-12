package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.jdbc.dao.LogUserDao;
import com.techelevator.tenmo.jdbc.dao.UserDao;
import com.techelevator.tenmo.model.LogUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@PreAuthorize("isAuthenticated()") //
@RestController
public class BaseController
{
    protected LogUserDao logUserDao;
    protected UserDao userDao;


    public BaseController(LogUserDao logUserDao, UserDao userDao)
    {
        this.logUserDao = logUserDao;
        this.userDao = userDao;
    }


    public LogUser getUser(Principal principal)
    {
        return logUserDao.findByUsername(principal.getName());

    }

}
