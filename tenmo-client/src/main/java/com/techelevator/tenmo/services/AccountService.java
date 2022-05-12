package com.techelevator.tenmo.services;

public class AccountService implements TokenService {

    private String authToken;

    @Override
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
