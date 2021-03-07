package com.tweetapp.tweet.service.impl;

import com.tweetapp.tweet.dao.UserDao;
import com.tweetapp.tweet.model.ReasonConstant;
import com.tweetapp.tweet.model.User;
import com.tweetapp.tweet.service.UserService;
import com.tweetapp.tweet.service.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public String signUp(User user, String dates) {
        String userValidation = UserValidator.validateUser(user, dates);
        if (userValidation.equalsIgnoreCase(ReasonConstant.valid)) {
            if (checkIfUserExists(user.getEmail()) == false) {
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

}
