package edu.najah.cap.delete;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.payment.IPayment;
import edu.najah.cap.posts.IPostService;
import edu.najah.cap.servicesfactories.ActivityServiceFactory;
import edu.najah.cap.servicesfactories.PaymentServiceFactory;
import edu.najah.cap.servicesfactories.PostServiceFactory;
import edu.najah.cap.servicesfactories.UserServiceFactory;

import java.security.InvalidParameterException;
import java.util.logging.Level;

import static edu.najah.cap.logs.MyLogging.log;

public class DeleteService {

    public static synchronized void deleteData(String dataType, String username) throws BadRequestException {
        try {
            DeleteIterator iterator = getIterator(dataType, username);
            if (!iterator.hasNext()) {
                throw new NotFoundException("No " + dataType + " Found for username: " + username);
            }
            while (iterator.hasNext()) {
                iterator.deleteCurrent();
            }
            log(Level.INFO, "All " + dataType + " Deleted for username: " + username, "DeleteService", "deleteData");
        } catch (NotFoundException e) {
            log(Level.INFO, "No " + dataType + " Found for username: " + username, "DeleteService", "deleteData");
        } catch (IllegalArgumentException e) {
            log(Level.SEVERE, e.getMessage(), "DeleteService", "deleteData");
        }
    }


    public static synchronized void deleteUser(String username) throws BadRequestException {
        while (true) {
            try {
                IUserService userService = UserServiceFactory.getUserService("UserServiceProxy");
                userService.deleteUser(username);
                log(Level.INFO, "User Deleted: " + username, "DeleteService", "deleteUser");
                return;
            } catch (NotFoundException e) {
                log(Level.INFO, "User Not Found: " + username, "DeleteService", "deleteUser");
                return;
            } catch (SystemBusyException e) {
                log(Level.WARNING, "System Busy, Trying Again...", "DeleteService", "deleteUser");
            }
        }
    }

    private static DeleteIterator getIterator(String dataType, String username) throws NotFoundException, BadRequestException, IllegalArgumentException {
        while (true) {
            try {
                if ("Posts".equals(dataType)) {
                    IPostService postService = PostServiceFactory.getPostService("PostService");
                    return new DeleteIterator<>(postService.getPosts(username));
                } else if ("Transactions".equals(dataType)) {
                    IPayment paymentService = PaymentServiceFactory.getPaymentService("PaymentService");
                    return new DeleteIterator<>(paymentService.getTransactions(username));
                } else if ("User Activities".equals(dataType)) {
                    IUserActivityService userActivityService = ActivityServiceFactory.getActivityService("ActivityService");
                    return new DeleteIterator<>(userActivityService.getUserActivity(username));
                } else {
                    throw new InvalidParameterException("Invalid Service Type");
                }
            } catch (SystemBusyException e) {
                log(Level.WARNING, "System Busy, Trying Again...", "DeleteService", "getIterator");
            }
        }
    }
}
