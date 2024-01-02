package edu.najah.cap.export;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.activity.UserActivity;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ExportActivity {
    public static void exportActivity(String user, IUserActivityService userActivityService) {
        try (PrintWriter activityWriter = new PrintWriter(new FileWriter(user + " Activity.txt"))) {
            for (UserActivity activity : userActivityService.getUserActivity(user)) {
                activityWriter.println("User ID: " + activity.getUserId());
                activityWriter.println("Activity Type: " + activity.getActivityType());
                activityWriter.println("Activity Date: " + activity.getActivityDate());
                activityWriter.println();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
