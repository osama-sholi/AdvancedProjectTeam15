package edu.najah.cap.servicesfactories;

import edu.najah.cap.posts.IPostService;
import edu.najah.cap.posts.PostService;

public class PostServiceFactory {
    public static IPostService getPostService(PostServiceTypes type) throws IllegalArgumentException{
        if (PostServiceTypes.POST_SERVICE.equals(type)) {
            return new PostService();
        }
        else {
            throw new IllegalArgumentException("Invalid post service");
        }
    }
}
