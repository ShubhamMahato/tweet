package com.tweetapp.tweet.dao;

import com.tweetapp.tweet.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao extends JpaRepository<Post,Integer> {
    List<Post> findByEmail (String email);
}
