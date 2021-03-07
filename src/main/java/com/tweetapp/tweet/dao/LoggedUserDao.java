package com.tweetapp.tweet.dao;

import com.tweetapp.tweet.model.LoggedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggedUserDao extends JpaRepository<LoggedUser,Integer> {
    LoggedUser findByEmail(String email);
}
