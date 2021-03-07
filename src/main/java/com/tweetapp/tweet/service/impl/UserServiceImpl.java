package com.tweetapp.tweet.service.impl;

import com.tweetapp.tweet.dao.UserDao;
import com.tweetapp.tweet.model.User;
import com.tweetapp.tweet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User signUp(User user) {
        userDao.save(user);
        return user;
    }

}
