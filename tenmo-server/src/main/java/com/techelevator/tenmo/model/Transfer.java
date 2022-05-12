package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer
{
    private String senderName;
    private String receiverName;
    private int transferId;
    private int transferTypeId;
    private int transferStatusId;
    private int accountFromId;
    private int accountToId;
    private BigDecimal amount;

    public Transfer() {

    }

    public Transfer( int accountFrom, int accountTo, BigDecimal amount, String senderName, String receiverName) {
        this.accountFromId = accountFrom;
        this.accountToId = accountTo;
        this.amount = amount;
        this.senderName = senderName;
        this.receiverName = receiverName;

    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public int getSenderAccountId() {
        return accountFromId;
    }

    public void setSenderAccountId(int senderAccountId) {
        this.accountFromId = senderAccountId;
    }

    public int getAccountToId() {
        return accountToId;
    }

    public void setAccountToId(int accountToId) {
        this.accountToId = accountToId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
