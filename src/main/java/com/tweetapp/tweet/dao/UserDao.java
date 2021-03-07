package com.tweetapp.tweet.dao;

import com.tweetapp.tweet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    User findByEmail(String email);

}
