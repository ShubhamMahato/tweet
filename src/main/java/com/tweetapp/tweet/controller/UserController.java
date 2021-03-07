package com.tweetapp.tweet.controller;

import com.tweetapp.tweet.dao.LoggedUserDao;
import com.tweetapp.tweet.model.User;
import com.tweetapp.tweet.service.PostService;
import com.tweetapp.tweet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserController implements CommandLineRunner {

    @Autowired
    UserService userService;


    @Autowired
    PostService postService;

    @Override
    public void run(String... args) throws Exception {

        LoggedInUser loggedInUser = new LoggedInUser();
        boolean flag = true;

        while(flag) {
           loggedInUser.UnloggedUserMenu(userService,postService);
        }

    }


}
