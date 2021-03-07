package com.tweetapp.tweet.service.impl;

import com.tweetapp.tweet.dao.LoggedUserDao;
import com.tweetapp.tweet.dao.PostDao;
import com.tweetapp.tweet.model.LoggedUser;
import com.tweetapp.tweet.model.Post;
import com.tweetapp.tweet.model.ReasonConstant;
import com.tweetapp.tweet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private LoggedUserDao loggedUserDao;

    @Override
    public String saveUsersPost(String post) {
        List<LoggedUser> loggedUsers = loggedUserDao.findAll();
        if (loggedUsers.size() == 1) {
            Post loggedUserPost = Post.builder().email(loggedUsers.get(0).getEmail()).post(post).build();
            postDao.save(loggedUserPost);
            return ReasonConstant.postSaved;
        } else {
            return ReasonConstant.pleaseTryLogOutAndLoginAgain;
        }
    }

    @Override
    public List<Post> getAllUsersPost() {
        List<LoggedUser> loggedUsers = loggedUserDao.findAll();
        List<Post> posts = new ArrayList<>();
        if (loggedUsers.size() == 1) {
            posts.addAll(postDao.findByEmail(loggedUsers.get(0).getEmail()));
            return posts;
        }
        return posts;
    }

    @Override
    public List<Post> getAllPost(){
        return postDao.findAll();
    }
}

