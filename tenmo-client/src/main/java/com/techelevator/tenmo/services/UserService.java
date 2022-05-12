package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.prefs.BackingStoreException;


public class UserService {

    private static final String BASE_URL = "http://localhost:8080/";
    private static final RestTemplate restTemplate = new RestTemplate();

    private String authToken  = null;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() { return authToken;}

    public BigDecimal getBalance()
    {
        String url = BASE_URL + "/myBalance";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<BigDecimal> response = restTemplate.exchange(url, HttpMethod.GET, entity, BigDecimal.class);

//        BigDecimal balance =  restTemplate.getForObject(url, BigDecimal.class);

        return response.getBody();
    }

    public User[] getAllUsers() {

        String url = BASE_URL + "/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<User[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, User[].class);

        return response.getBody();
    }

    public Integer getId(String username)
    {
        String url = BASE_URL + "/users/" + username;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);

        HttpEntity<Void> entity = new HttpEntity<>( headers);

        ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, entity, Integer.class);

        return response.getBody();

    }

    public User getUserById(int id)
    {
        String url = BASE_URL + "/user/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);

        HttpEntity<Void> entity = new HttpEntity<>( headers);

        ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.GET, entity, User.class);

        return response.getBody();

    }




    // Service to connect to the server to find all the users

    // get all user method

    // choose a user method

    // change balance on the user that sends a boolean for the current user to update.


}
