package com.techelevator.tenmo.view;

import com.techelevator.tenmo.model.CashRegister;

import java.util.Scanner;

public abstract class ViewBase {
    protected Scanner input = new Scanner(System.in);

    private static CashRegister cashRegister = new CashRegister();

    public static  void setCashRegister(CashRegister cashRegister) {
        ViewBase.cashRegister = cashRegister;
    }

    public abstract void display();

    public void displayBalance()
    {
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.printf( "Balance: $f \n", cashRegister.getBalance());
        System.out.println("--------------------------------------");
    }


}
