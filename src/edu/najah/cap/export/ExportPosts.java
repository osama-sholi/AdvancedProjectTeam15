package edu.najah.cap.export;

import edu.najah.cap.posts.IPostService;
import edu.najah.cap.posts.Post;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ExportPosts {
    public static void exportPosts(String user, IPostService postService) {
        try (PrintWriter postWriter = new PrintWriter(new FileWriter(user + " Posts.txt"))) {
            for (Post post : postService.getPosts(user)) {
                postWriter.println("Post Title: " + post.getTitle());
                postWriter.println("Post Body: " + post.getBody());
                postWriter.println("Post Author: " + post.getAuthor());
                postWriter.println("Post Date: " + post.getDate());
                postWriter.println();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
