package edu.najah.cap.export.exportservices;

import edu.najah.cap.convertor.PDFConvertor;
import edu.najah.cap.posts.IPostService;
import edu.najah.cap.posts.Post;
import edu.najah.cap.servicesfactories.PostServiceFactory;
import edu.najah.cap.servicesfactories.PostServiceTypes;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;

import static edu.najah.cap.logs.MyLogging.log;

public class ExportPost implements IExportService {
    @Override
    public String exportData(String user, String path) throws Exception {
        log(Level.INFO, "Exporting posts to local storage...", "ExportPosts", "exportPosts");
        String outputPath = path + user + "-Posts.txt";
        try (PrintWriter postWriter = new PrintWriter(new FileWriter(outputPath))) {
            IPostService postService = PostServiceFactory.getPostService(PostServiceTypes.POST_SERVICE);
            List<Post> posts = null;
            while(true){
                try {
                    posts = postService.getPosts(user);
                    break;
                } catch (Exception e) {
                    log(Level.WARNING, "System Busy, Trying Again...","ExportPosts", "exportPosts");
                }
            }
            for (Post post : posts) {
                postWriter.println("Post Title: " + post.getTitle());
                postWriter.println("Post Body: " + post.getBody());
                postWriter.println("Post Author: " + post.getAuthor());
                postWriter.println("Post Date: " + post.getDate());
                postWriter.println();
            }
            postWriter.close();
            return PDFConvertor.ConvertToPdf(outputPath);
        }
    }
}
