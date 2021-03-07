package com.tweetapp.tweet.service;

import com.tweetapp.tweet.model.Post;

import java.util.List;

public interface PostService {
    String saveUsersPost(String post);
    List<Post> getAllUsersPost();
    List<Post> getAllPost();
}
