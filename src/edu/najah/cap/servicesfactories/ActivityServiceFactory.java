package edu.najah.cap.servicesfactories;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.activity.UserActivityService;

public class ActivityServiceFactory {
    public static IUserActivityService getActivityService(String activityService) throws IllegalArgumentException{
        if ("ActivityService".equals(activityService)) {
            return new UserActivityService();
        }
        else {
            throw new IllegalArgumentException("Invalid activity service");
        }
    }
}
