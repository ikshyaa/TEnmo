package com.techelevator.tenmo.jdbc.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao
{

    public void transferMoney();

    public Transfer getTransferById(long id);

    public int createTransfer(Transfer transfer);

    public List<Transfer> getPendingTransfers();

    public List<Transfer> getApprovedTransfers();

    public List<Transfer> getRejectedTransfers();

    public List<Transfer> getAllTransfers(int account_id);
}
