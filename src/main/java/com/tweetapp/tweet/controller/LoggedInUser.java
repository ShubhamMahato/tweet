package com.tweetapp.tweet.controller;

import com.tweetapp.tweet.model.ReasonConstant;
import com.tweetapp.tweet.model.User;
import com.tweetapp.tweet.service.PostService;
import com.tweetapp.tweet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;


public class LoggedInUser {
    Logger logger = LoggerFactory.getLogger(LoggedInUser.class);

    public void UnloggedUserMenu(UserService userService,PostService postService){
        Scanner sc=new Scanner(System.in);
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Forgot Password");
        System.out.println("Enter your choice");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                registerUserMenu(userService);
                break;
            case 2:
                logInUserMenu(userService,postService);
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    public void loggedInUserMenu(UserService userService,PostService postService) {
        boolean flag=true;
        while(flag) {
            Scanner sc = new Scanner(System.in);
            System.out.println("i. Post a tweet");
            System.out.println("ii. View my tweets");
            System.out.println("iii. View all tweets");
            System.out.println("iv. View All Users");
            System.out.println("v. Reset Password");
            System.out.println("vi. Logout");
            System.out.println("Choose an Option");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("1. Post a tweet");
                    System.out.println("enter the post");
                    String post = sc.nextLine();
                    System.out.println(postService.saveUsersPost(post));
                    break;
                case 2:
                    System.out.println("2. View my tweets");
                    System.out.println(postService.getAllUsersPost());
                    break;
                case 3:
                    System.out.println("3. View all tweets");
                    System.out.println(postService.getAllPost());
                    break;
                case 4:
                    System.out.println("4. View All Users");
                    System.out.println(userService.getAllUsers());
                    break;
                case 5:
                    System.out.println("5. Reset Password");
                    break;
                case 6:
                    System.out.println("vi. Logout");
                    flag=false;
                    break;
                default:
                    break;
            }
        }
    }

    public void registerUserMenu(UserService userService){
        Scanner sc = new Scanner(System.in);
        User user = new User();
        String dates = null;
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
        System.out.println(userService.signUp(user,dates));
    }

    public void logInUserMenu(UserService userService, PostService postService){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the email");
        String email=sc.nextLine();
        System.out.println("enter the password");
        String password=sc.nextLine();
        if(userService.loginUser(email,password).equalsIgnoreCase(ReasonConstant.userLoggedIn)){
            loggedInUserMenu(userService,postService);
        }
    }
}
