package edu.najah.cap.delete;

import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.activity.UserActivityService;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.payment.PaymentService;
import edu.najah.cap.payment.Transaction;
import edu.najah.cap.posts.Post;
import edu.najah.cap.posts.PostService;
import edu.najah.cap.proxy.UserServiceProxy;

public class HardDelete extends AbstractDelete {
    public void delete(String username){
        System.out.println("Hard Delete for user: " + username);
        try {
            // Delete all posts of the username
            PostService postService = new PostService();
            DeleteIterator<Post> postsIterator = new DeleteIterator<>(postService.getPosts(username));
            deleteData(postsIterator, "Posts");

            // Delete all transactions of the username
            PaymentService paymentService = new PaymentService();
            DeleteIterator<Transaction> transactionsIterator = new DeleteIterator<>(paymentService.getTransactions(username));
            deleteData(transactionsIterator, "Transactions");

            // Delete all activities of the username
            UserActivityService userActivityService = new UserActivityService();
            DeleteIterator<UserActivity> userActivitiesIterator = new DeleteIterator<>(userActivityService.getUserActivity(username));
            deleteData(userActivitiesIterator, "User Activities");

            // Delete user
            IUserService userService = new UserServiceProxy();
            userService.deleteUser(username);
            System.out.println("User Deleted");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
