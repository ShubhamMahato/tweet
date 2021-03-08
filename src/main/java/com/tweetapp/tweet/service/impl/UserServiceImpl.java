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

    @Override
    public String signUp(User user, String dates) {
        String userValidation = UserValidator.validateUser(user, dates);
        if (userValidation.equalsIgnoreCase(ReasonConstant.valid)) {
            if (checkIfUserExists(user.getEmail())) {
                userDao.save(user);
                return ReasonConstant.userRegistered;
            } else {
                return ReasonConstant.emailAlreadyRegistered;
            }
        } else {
            return userValidation;
        }
    }

    public boolean checkIfUserExists(String email) {
        return userDao.findByEmail(email) == null;
    }

    public String loginUser(String email, String password) {
        if (userDao.findByEmailAndPassword(email, password) != null) {
            LoggedUser loggedUser = LoggedUser.builder().email(email).build();
            if (!loggedUserDao.findAll().isEmpty()) {
                loggedUserDao.deleteAll();
                loggedUserDao.save(loggedUser);
                return ReasonConstant.userLoggedIn;
            } else {
                loggedUserDao.save(loggedUser);
                return ReasonConstant.userLoggedIn;
            }
        } else {
            return ReasonConstant.invalidCredentials;
        }
    }

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

    @Override
    public String forgotPassword(String email, String oldPassword, String newPassword) {
        User user = userDao.findByEmailAndPassword(email, oldPassword);
        if (user == null) {
            return ReasonConstant.oldPasswordNotCorrect;
        } else {
            user.setPassword(newPassword);
            userDao.save(user);
            return ReasonConstant.passwordChanged;
        }
    }

    @Override
    public String logout() {
        loggedUserDao.deleteAll();
        return ReasonConstant.loggedOut;
    }

}
