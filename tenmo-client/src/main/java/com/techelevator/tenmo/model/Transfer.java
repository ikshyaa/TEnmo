package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.security.Principal;

public class Transfer {

    private String senderName;
    private String receiverName;
    private int transferId;
    private int transferTypeId;
    private int transferStatusId;
    private int accountFromId;
    private int accountToId;
    private BigDecimal amount;

    public Transfer( int accountFrom, int accountTo, BigDecimal amount, String senderName, String receiverName) {
        this.accountFromId = accountFrom;
        this.accountToId = accountTo;
        this.amount = amount;
        this.senderName = senderName;
        this.receiverName = receiverName;

    }

    public Transfer() {}

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

    public long getAccountFrom() {
        return accountFromId;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFromId = accountFrom;
    }

    public long getAccountTo() {
        return accountToId;
    }

    public void setAccountTo(int accountTo) {
        this.accountToId = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transfer {" +
                "Transfer Id =" + transferId +
                "\nSender Name =" + senderName +
                "\nReceiver Name =" + receiverName +
                "\nTransfer Type Id =" + transferTypeId +
                "\nTransfer Status Id =" + transferStatusId +
                "\nAmount =" + amount ;
    }

    //
//    Principal principal;
//
//    String transferFrom = principal.getName();
//    String transferTo;
//    Integer teBucks;

}
