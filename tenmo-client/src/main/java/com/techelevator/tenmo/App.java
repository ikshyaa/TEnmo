package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.*;

import java.math.BigDecimal;
import java.security.Principal;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";
    private CashRegister cashRegister = new CashRegister();

    private UserService userService = new UserService();
    private TransferService transferService = new TransferService();
    private AccountService accountService = new AccountService();


    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);


    private AuthenticatedUser currentUser;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
//        ViewBase.setCashRegister(cashRegister);
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        } else {
            String token = currentUser.getToken();
            userService.setAuthToken(token);
            transferService.setAuthToken(token);
            accountService.setAuthToken(token);
            // added code



        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance()
    {
        BigDecimal balance=  userService.getBalance();
        consoleService.displayBalance(balance);
	}

	private void viewTransferHistory() {
        Transfer[] transfers = transferService.getPastTransfers();
        consoleService.displayTransfers(transfers);
        long transfer_id = (long)consoleService.promptForInt("Enter Transfer_id for details.");
        Transfer transfer = transferService.getTransfer(transfer_id);
        consoleService.displayTransfer(transfer);
		// TODO Auto-generated method stub
		
	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks()
    {
        User[] users = userService.getAllUsers();
        consoleService.displayUsers(users);
        Transfer transfer = new Transfer();
        boolean sameUser = true;
        String sendToUser = "";
        BigDecimal transferAmount = BigDecimal.ZERO;

        while(sameUser) {

            sendToUser = consoleService.promptForString("choose a User by username to send money to! ");
            transferAmount = BigDecimal.valueOf(consoleService.promptForInt("How much would you like to send? "));
            if(sendToUser.equals(currentUser.getUser().getUsername()))
            {
                consoleService.displayFailure("Transfer to own account");

            }
            else sameUser = false;
        }

        transfer.setSenderName(currentUser.getUser().getUsername());
        transfer.setReceiverName(sendToUser);
        transfer.setAmount(transferAmount);

        Transfer successfulTransfer =  transferService.sendMoney(transfer);

        if(successfulTransfer != null) consoleService.displaySuccessMessage("Transfer");

	}

	private void requestBucks() {
		// TODO Auto-generated method stub
        User[] users = userService.getAllUsers();
        consoleService.displayUsers(users);
		
	}

}
