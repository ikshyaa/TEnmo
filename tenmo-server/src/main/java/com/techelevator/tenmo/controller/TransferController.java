package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.jdbc.dao.AccountDao;
import com.techelevator.tenmo.jdbc.dao.LogUserDao;
import com.techelevator.tenmo.jdbc.dao.TransferDao;
import com.techelevator.tenmo.jdbc.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController extends BaseController

{
    @Autowired
    public TransferController(LogUserDao logUserDao, UserDao userDao) {
        super(logUserDao, userDao);
    }

    @Autowired
    TransferDao transferDao;

    @Autowired
    AccountDao accountDao;

    @ApiOperation("Sending money to another user")
    @Transactional
    @PostMapping ("/send")
    public Transfer sendMoney(@RequestBody @ApiParam("Transfer Object with transfer information") Transfer transfer, Principal principal) {

        int accountFromId = accountDao.getAccountIdByUsername(transfer.getSenderName());
        int accountToId = accountDao.getAccountIdByUsername(transfer.getReceiverName());
        transfer.setSenderAccountId(accountFromId);
        transfer.setAccountToId(accountToId);

        int transferId= transferDao.createTransfer(transfer);



        Boolean isTransferDone = accountDao.transfer(transfer);

        return transferDao.getTransferById(transferId);
    }

    @ApiOperation("Get all transfer")
    @GetMapping ("")
    public List<Transfer> getAllTransfer(@ApiParam("Logged in user to get transactions for") Principal principal)
    {
        String userName = principal.getName();
        int account_id = accountDao.getAccountIdByUsername(userName);

        List<Transfer> transfers =  transferDao.getAllTransfers(account_id);

        return transfers;

    }

    @ApiOperation("Get all transfer")
    @GetMapping ("/{id}")
    public Transfer getTransfer(@ApiParam("get details of a selected Transfer") @PathVariable int id)
    {

        return transferDao.getTransferById(id);

    }









    // send == transfer ?

}
