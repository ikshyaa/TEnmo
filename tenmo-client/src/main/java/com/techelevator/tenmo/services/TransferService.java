package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

public class TransferService implements TokenService{

    private String authToken;

    private static final String BASE_URL = "http://localhost:8080/";
    private static final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    UserService userService;

    @Override
    public void setAuthToken(String authToken) {
        this.authToken = authToken;

    }

    public Transfer[] getPastTransfers()
    {
        String url = BASE_URL + "/transfer";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Transfer[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Transfer[].class);


        return responseEntity.getBody();
    }


    public Transfer sendMoney(Transfer transfer) {
        transfer.setTransferStatusId(2);
        transfer.setTransferTypeId(2);

        String url = BASE_URL + "/transfer/send";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);

        ResponseEntity<Transfer> response = restTemplate.exchange(url, HttpMethod.POST, entity, Transfer.class);
        // sent transfer data to the server along with authToken.
        // Balance - call to userService for balance - currentUser
        // If Condition -- check for current user balance if can make transfer -- server
        // withdraw from current user
        // deposit to toUser
        // change status of transfer
        return response.getBody();
    }

    public Transfer getTransfer(long transfer_id)
    {
        String url = BASE_URL + "/transfer/"+ transfer_id;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);

        HttpEntity<Void> entity = new HttpEntity<>( headers);

        ResponseEntity<Transfer> response = restTemplate.exchange(url, HttpMethod.GET, entity, Transfer.class);

        return response.getBody();
    }



    public boolean withdraw(User user, int amount)
    {
        return false;
    }


    public boolean deposit(User user, int amount)
    {
        return false;
    }



}
