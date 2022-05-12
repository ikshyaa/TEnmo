package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

    public void displayBalance(BigDecimal balance) {

        System.out.println();
        System.out.println("Your current balance is: $" + balance);
    }

    public void displayUsers(User[] user)
    {
        System.out.println("UserId \t \t UserName");
        for (int i = 0; i < user.length; i++) {
            System.out.println( user[i].getId()+"\t\t\t" + user[i].getUsername());
        }
    }

    public void displaySuccessMessage(String string) {

        System.out.println("-----------------------------------------------");
        System.out.printf("|           %s WAS A SUCCESS!!         |\n", string);
        System.out.println("-----------------------------------------------");
    }

    public void displayFailure(String string)
    {
        System.out.println("-----------------------------------------------");
        System.out.printf("|        Failed: %s         |\n", string);
        System.out.println("-----------------------------------------------");
    }

    public void displayTransfers(Transfer[] transfers) {

        System.out.println("----------------------------------------------------------------------------------\n" +
                "Transfer Id " +
                "\tSender Name " +
                "\tReceiver Name " +
                "\tTransfer Type Id " +
                "\tTransfer Status Id " +
                "\tAmount " );
        for(Transfer transfer:transfers)
        {
            System.out.println(transfer.getTransferId() +
                    "\t\t\t" + transfer.getSenderName() +
                    "\t\t\t" + transfer.getReceiverName() +
                    "\t\t\t\t" + transfer.getTransferTypeId() +
                    "\t\t\t\t" + transfer.getTransferStatusId() +
                    "\t\t\t" + transfer.getAmount());
            System.out.println("-----------------------------------------------------------------------------------\n");
        }
    }
    public void displayTransfer(Transfer transfer)
    {
        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.println("\n\n\t|Transfer Id: "+ transfer.getTransferId() +
                "\n\t|Sender Name: " + transfer.getSenderName() +
                "\n\t|Receiver Name: " + transfer.getReceiverName() +
                "\n\t|Transfer Type Id: " + transfer.getTransferTypeId() +
                "\n\t|Transfer Status Id: " + transfer.getTransferStatusId() +
                "\n\t|Amount: " + transfer.getAmount());
        System.out.println("------------------------------------------------");
    }
}
