package com.techelevator.tenmo.jdbc;

import com.techelevator.tenmo.jdbc.dao.AccountDao;
import com.techelevator.tenmo.jdbc.dao.TransferDao;
import com.techelevator.tenmo.jdbc.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountDao accountDao;

    @Autowired
    UserDao userDao;



    @Override
    public void transferMoney() {

    }

    public List<Transfer> getAllTransfers(int account_id){

        List<Transfer> transfers = new LinkedList<>();
        String sql = "SELEcT transfer_id " +
                    ", transfer_type_id " +
                    ", transfer_status_id " +
                    ", account_from " +
                    ", account_to " +
                    ", amount " +
                    "from transfer " +
                    "where account_from = ? " +
                    "OR account_to = ? " +
                    ";";

        SqlRowSet rowSet= jdbcTemplate.queryForRowSet(sql, account_id, account_id);

        while(rowSet.next())
        {
            Transfer transfer = rowToTransfer(rowSet);
            transfers.add(transfer);
        }

        return transfers;
    }

    @Override
    public Transfer getTransferById(long id) {
        Transfer transfer = new Transfer();
        String sql = "SELECT transfer_id" +
                     " , transfer_type_id " +
                     " , transfer_status_id " +
                     " , account_from " +
                     " , account_to " +
                     " , amount " +
                     " FROM transfer " +
                     " WHERE transfer_id = ? ;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);

        if(rowSet.next())
        {
            transfer = rowToTransfer(rowSet);
        }
        return transfer;
    }

    private Transfer rowToTransfer(SqlRowSet rowSet) {

        Transfer transfer = new Transfer();

        int transferId = (rowSet.getInt("transfer_id"));
        int transferTypeId = rowSet.getInt("transfer_type_id");
        int transferStatus = rowSet.getInt("transfer_status_id");
        int transferFrom  = rowSet.getInt("account_from");
        int transferTo = rowSet.getInt("account_to");
        BigDecimal amount = rowSet.getBigDecimal("amount");

       transfer.setTransferId(transferId);
       transfer.setTransferTypeId(transferTypeId);
       transfer.setTransferStatusId(transferStatus);
       transfer.setSenderAccountId(transferFrom);
       transfer.setAccountToId(transferTo);
       transfer.setAmount(amount);
       transfer.setSenderName(userDao.getUserName(transferFrom));
       transfer.setReceiverName(userDao.getUserName(transferTo));

       return transfer;
    }

    @Override
    public int createTransfer(Transfer transfer) {

        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount)\n" +
                "VALUES (?, ?, ?, ?, ?) " +
                "RETURNING transfer_id;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql
                , transfer.getTransferTypeId()
                , transfer.getTransferStatusId()
                , transfer.getSenderAccountId()
                , transfer.getAccountToId()
                , transfer.getAmount());

        if(rowSet.next()) {
            return rowSet.getInt("transfer_id");
        }
        return 0;
    }

    @Override
    public List<Transfer> getPendingTransfers() {
        return null;
    }

    @Override
    public List<Transfer> getApprovedTransfers() {
        return null;
    }

    @Override
    public List<Transfer> getRejectedTransfers() {
        return null;
    }

}
