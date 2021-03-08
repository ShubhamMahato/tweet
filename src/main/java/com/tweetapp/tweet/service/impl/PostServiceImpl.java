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

    /**
     * save the users post
     * @param post
     * @return
     */
    @Override
    public String saveUsersPost(String post) {
        // get the currently logged in user
        List<LoggedUser> loggedUsers = loggedUserDao.findAll();
        if (loggedUsers.size() == 1) {
            //check if post is valid not null
            if (post != null && post.length() > 0) {
                Post loggedUserPost = Post.builder().email(loggedUsers.get(0).getEmail()).post(post).build();
                //save the post
                postDao.save(loggedUserPost);
                return ReasonConstant.postSaved;
            } else {
                //invalid post if invalid
                return ReasonConstant.invalidPost;
            }
        } else {
            return ReasonConstant.pleaseTryLogOutAndLoginAgain;
        }
    }

    /**
     * get all the users post
     * @return
     */
    @Override
    public List<Post> getAllUsersPost() {
        //get currently logged in user
        List<LoggedUser> loggedUsers = loggedUserDao.findAll();
        List<Post> posts = new ArrayList<>();
        if (loggedUsers.size() == 1) {
            posts.addAll(postDao.findByEmail(loggedUsers.get(0).getEmail()));
            for (Post post : posts) {
                //printing the post in structured way
                System.out.print("USER:--" + post.getEmail() + " ");
                System.out.println("POST:--" + post.getPost() + "\n");
            }
            return posts;
        }
        return posts;
    }

    /**
     * get all the post from all users
     * @return
     */

    @Override
    public List<Post> getAllPost() {
        // find the posts in db
        List<Post> posts = postDao.findAll();
        if (posts != null && !posts.isEmpty()) {
            for (Post post : posts) {
                System.out.print("USER:--" + post.getEmail() + " ");
                System.out.println("POST:--" + post.getPost() + "\n");
            }
        }
        return posts;
    }
}

