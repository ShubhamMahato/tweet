package com.tweetapp.tweet.controller;

import com.tweetapp.tweet.model.ReasonConstant;
import com.tweetapp.tweet.model.User;
import com.tweetapp.tweet.service.UserService;
import com.tweetapp.tweet.service.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Component
public class UserController implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        String dates = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the email id");
        user.setEmail(sc.nextLine().trim());
        System.out.println("Enter the FirstName");
        user.setFirstName(sc.nextLine().trim());
        
        System.out.println("do you want to enter lastname press y");
        String checkLastName=sc.nextLine();
        if(checkLastName.equalsIgnoreCase("y")) {
            System.out.println("Enter the LastName");
            user.setLastName(sc.nextLine().trim());
        }
        System.out.println("do you want to enter date of birth press y");
        String checkDOB=sc.nextLine();
        if(checkDOB.equalsIgnoreCase("y")) {
            System.out.println("Enter the dob");
            System.out.println("dd-mm-yyyy");
            dates = sc.nextLine().trim();
        }
        System.out.println("Enter the Gender");
        user.setGender(sc.next().trim());
        sc.nextLine();
        System.out.println("Enter the password");
        user.setPassword(sc.nextLine().trim());
        System.out.println(user.toString());
        System.out.println(userService.signUp(user,dates));


    }
}
