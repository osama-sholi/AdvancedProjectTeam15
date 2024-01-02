package edu.najah.cap.servicesfactories;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.activity.UserActivityService;

public class ActivityServiceFactory {
    public static IUserActivityService getActivityService(ActivityServiceTypes type) throws IllegalArgumentException{
        if (ActivityServiceTypes.ACTIVITY_SERVICE.equals(type)) {
            return new UserActivityService();
        }
        else {
            throw new IllegalArgumentException("Invalid activity service");
        }
    }
}
