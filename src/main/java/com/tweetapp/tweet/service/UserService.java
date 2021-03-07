package com.tweetapp.tweet.service;


import com.tweetapp.tweet.model.User;

public interface UserService {

    String signUp(User user,String date);
    boolean checkIfUserExists(String email);
}
