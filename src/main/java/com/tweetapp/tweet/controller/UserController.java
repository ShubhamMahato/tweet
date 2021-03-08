package com.tweetapp.tweet.controller;

import com.tweetapp.tweet.service.PostService;
import com.tweetapp.tweet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserController implements CommandLineRunner {

    @Autowired
    UserService userService;


    @Autowired
    PostService postService;

    /**
     * Menu for Not logged in user
     * @param args
     * @throws Exception
     */

    @Override
    public void run(String... args) throws Exception {

        LoggedInUser loggedInUser = new LoggedInUser();
        boolean flag = true;

        while(flag) {
            //keep calling the unlogged menu
           loggedInUser.UnloggedUserMenu(userService,postService);
        }

    }


}
