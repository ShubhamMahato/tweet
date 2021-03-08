package com.tweetapp.tweet.service.impl;

import com.tweetapp.tweet.dao.LoggedUserDao;
import com.tweetapp.tweet.dao.UserDao;
import com.tweetapp.tweet.model.LoggedUser;
import com.tweetapp.tweet.model.ReasonConstant;
import com.tweetapp.tweet.model.User;
import com.tweetapp.tweet.service.UserService;
import com.tweetapp.tweet.service.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    LoggedUserDao loggedUserDao;

    /**
     * User signup
     * @param user
     * @param dates
     * @return
     */
    @Override
    public String signUp(User user, String dates) {
        //validate user details
        String userValidation = UserValidator.validateUser(user, dates);
        //matching if user is valid
        if (userValidation.equalsIgnoreCase(ReasonConstant.valid)) {
            //check if user exists
            if (checkIfUserExists(user.getEmail())) {
                //save user
                userDao.save(user);
                return ReasonConstant.userRegistered;
            } else {
                //give error
                return ReasonConstant.emailAlreadyRegistered;
            }
        } else {
            return userValidation;
        }
    }

    /**
     * check if user exists in db
     * @param email
     * @return
     */
    public boolean checkIfUserExists(String email) {
        return userDao.findByEmail(email) == null;
    }

    /**
     * UserLogin
     * @param email
     * @param password
     * @return
     */
    public String loginUser(String email, String password) {
        // find user by email and password
        if (userDao.findByEmailAndPassword(email, password) != null) {
            //add user to login state
            LoggedUser loggedUser = LoggedUser.builder().email(email).build();
            if (!loggedUserDao.findAll().isEmpty()) {
                //deleting if user exists already
                loggedUserDao.deleteAll();
                //saving currently logged in user
                loggedUserDao.save(loggedUser);
                return ReasonConstant.userLoggedIn;
            } else {
                //save user without deleting
                loggedUserDao.save(loggedUser);
                return ReasonConstant.userLoggedIn;
            }
        } else {
            return ReasonConstant.invalidCredentials;
        }
    }

    /**
     * get all the users excepts logged in
     * @return
     */
    public List<User> getAllUsers() {
        List<User> allUsers = userDao.findAll();
        LoggedUser loggedUser = loggedUserDao.findAll().get(0);
        if (!allUsers.isEmpty()) {
            allUsers = allUsers.stream()
                    .filter(a -> !a.getEmail().equalsIgnoreCase(loggedUser.getEmail()))
                    .collect(Collectors.toList());
        }
        for (User user : allUsers) {
            System.out.print("EMAIL" + ":--" + user.getEmail() + " ");
            System.out.print("FIRST NAME" + ":--" + user.getFirstName() + " ");
            System.out.println("\n");
        }
        return allUsers;
    }

    /**
     * forgot user passwords
     * @param email
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    public String forgotPassword(String email, String oldPassword, String newPassword) {
        //find if users exists and password is correct
        User user = userDao.findByEmailAndPassword(email, oldPassword);
        if (user == null) {
            //if not found return error
            return ReasonConstant.oldPasswordNotCorrect;
        } else {
            //change the password and save
            user.setPassword(newPassword);
            userDao.save(user);
            return ReasonConstant.passwordChanged;
        }
    }

    /**
     * logout the user
     * @return
     */
    @Override
    public String logout() {
        loggedUserDao.deleteAll();
        return ReasonConstant.loggedOut;
    }

}
