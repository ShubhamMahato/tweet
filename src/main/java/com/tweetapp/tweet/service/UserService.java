package com.tweetapp.tweet.service;


import com.tweetapp.tweet.model.LoggedUser;
import com.tweetapp.tweet.model.User;

import java.util.List;

public interface UserService {

    String signUp(User user,String date);
    boolean checkIfUserExists(String email);
    String loginUser(String email, String password);
    List<User> getAllUsers();
    String forgotPassword(String email, String oldPassword, String newPassword);

}
