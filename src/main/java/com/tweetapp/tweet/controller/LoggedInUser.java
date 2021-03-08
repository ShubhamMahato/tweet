package com.tweetapp.tweet.controller;

import com.tweetapp.tweet.model.Post;
import com.tweetapp.tweet.model.ReasonConstant;
import com.tweetapp.tweet.model.User;
import com.tweetapp.tweet.service.PostService;
import com.tweetapp.tweet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;


public class LoggedInUser {

    /**
     * Unlogged User menu
     * @param userService
     * @param postService
     */

    public void UnloggedUserMenu(UserService userService, PostService postService) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Forgot Password");
        System.out.println("Enter your choice");
        int choice = 0;
        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            System.out.println(ReasonConstant.wrongInput);
            UnloggedUserMenu(userService, postService);
        }
        switch (choice) {
            case 1:
                registerUserMenu(userService);
                break;
            case 2:
                logInUserMenu(userService, postService);
                break;
            case 3:
                System.out.println("5. Reset Password");
                sc.nextLine();
                System.out.println("Enter your email address");
                String email = sc.nextLine().trim();
                System.out.println("enter your old password");
                String oldPassword = sc.nextLine().trim();
                System.out.println("enter  your new password");
                String newPassword = sc.nextLine().trim();
                if (email != null && oldPassword != null && newPassword != null) {
                    System.out.println(userService.forgotPassword(email, oldPassword, newPassword));
                } else {
                    System.out.println(ReasonConstant.invalidCredentials);
                }
                break;
            default:
                System.out.println("WRONG OPTIONS");
                break;
        }
    }

    /**
     * Logged in user menu
     * @param userService
     * @param postService
     */
    public void loggedInUserMenu(UserService userService, PostService postService) {
        boolean flag = true;
        while (flag) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1. Post a tweet");
            System.out.println("2. View my tweets");
            System.out.println("3. View all tweets");
            System.out.println("4. View All Users");
            System.out.println("5. Reset Password");
            System.out.println("6. Logout");
            System.out.println("Choose an Option");
            int choice = 0;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println(ReasonConstant.wrongInput);
            }
            switch (choice) {
                case 1:
                    System.out.println("1. Post a tweet");
                    System.out.println("enter the post");
                    sc.nextLine();
                    String post = sc.nextLine().trim();
                    System.out.println(postService.saveUsersPost(post));
                    break;
                case 2:
                    System.out.println("2. View my tweets");
                    List<Post> getMyTweets = postService.getAllUsersPost();
                    if (getMyTweets != null && !getMyTweets.isEmpty()) {

                        System.out.println("POST ENDED");
                    } else {
                        System.out.println(ReasonConstant.noPostAvailable);
                    }
                    break;
                case 3:
                    System.out.println("3. View all tweets");
                    List<Post> getAllTweets = postService.getAllPost();
                    if (getAllTweets != null && !getAllTweets.isEmpty()) {
                        System.out.println("POST ENDED");
                    } else {
                        System.out.println(ReasonConstant.noPostAvailable);
                    }
                    break;
                case 4:
                    System.out.println("4. View All Users");
                    List<User> allUsers = userService.getAllUsers();
                    if (!allUsers.isEmpty()) {
                        System.out.println("USER FINISHED");
                    } else {
                        System.out.println(ReasonConstant.noUserFound);
                    }
                    break;
                case 5:
                    System.out.println("5. Reset Password");
                    sc.nextLine();
                    System.out.println("Enter your email address");
                    String email = sc.nextLine().trim();
                    System.out.println("enter your old password");
                    String oldPassword = sc.nextLine().trim();
                    System.out.println("enter  your new password");
                    String newPassword = sc.nextLine().trim();
                    if (email != null && oldPassword != null && newPassword != null) {
                        System.out.println(userService.forgotPassword(email, oldPassword, newPassword));
                        userService.logout();
                        flag = false;
                    } else {
                        System.out.println(ReasonConstant.invalidCredentials);
                    }
                    break;
                case 6:
                    System.out.println("vi. Logout");
                    System.out.println(userService.logout());
                    flag = false;
                    break;
                default:
                    System.out.println("WRONG OPTIONS");
                    break;
            }
        }
    }

    /**
     * Registration menu
     * @param userService
     */
    public void registerUserMenu(UserService userService) {
        Scanner sc = new Scanner(System.in);
        User user = new User();
        String dates = null;
        System.out.println("Enter the email id");
        user.setEmail(sc.nextLine().trim());
        System.out.println("Enter the FirstName");
        user.setFirstName(sc.nextLine().trim());

        System.out.println("do you want to enter lastname press y");
        String checkLastName = sc.nextLine();
        if (checkLastName.equalsIgnoreCase("y")) {
            System.out.println("Enter the LastName");
            user.setLastName(sc.nextLine().trim());
        }
        System.out.println("do you want to enter date of birth press y");
        String checkDOB = sc.nextLine();
        if (checkDOB.equalsIgnoreCase("y")) {
            System.out.println("Enter the dob");
            System.out.println("dd-mm-yyyy");
            dates = sc.nextLine().trim();
        }
        System.out.println("Enter the Gender");
        user.setGender(sc.next().trim());
        sc.nextLine();
        System.out.println("Enter the password");
        user.setPassword(sc.nextLine().trim());
        System.out.println(userService.signUp(user, dates));
    }

    /**
     * get user Logged in Menu
     * @param userService
     * @param postService
     */
    public void logInUserMenu(UserService userService, PostService postService) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the email");
        String email = sc.nextLine().trim();
        System.out.println("enter the password");
        String password = sc.nextLine().trim();
        if (userService.loginUser(email, password).equalsIgnoreCase(ReasonConstant.userLoggedIn)) {
            System.out.println(ReasonConstant.userLoggedIn);
            loggedInUserMenu(userService, postService);
        } else {
            System.out.println(ReasonConstant.invalidCredentials);
        }
    }
}
