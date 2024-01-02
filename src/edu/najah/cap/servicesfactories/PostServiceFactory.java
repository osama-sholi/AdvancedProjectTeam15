package edu.najah.cap.servicesfactories;

import edu.najah.cap.posts.IPostService;
import edu.najah.cap.posts.PostService;

public class PostServiceFactory {
    public static IPostService getPostService(String postService) throws IllegalArgumentException{
        if ("PostService".equals(postService)) {
            return new PostService();
        }
        else {
            throw new IllegalArgumentException("Invalid post service");
        }
    }
}
