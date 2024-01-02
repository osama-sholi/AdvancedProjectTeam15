package edu.najah.cap.export.exportservices;

import edu.najah.cap.activity.IUserActivityService;
import edu.najah.cap.activity.UserActivity;
import edu.najah.cap.compress.PDFConvertor;
import edu.najah.cap.exceptions.FileDeletionException;
import edu.najah.cap.exceptions.NotFoundException;
import edu.najah.cap.exceptions.SystemBusyException;
import edu.najah.cap.exceptions.UnqualifiedUserException;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.iam.UserType;
import edu.najah.cap.servicesfactories.ActivityServiceFactory;
import edu.najah.cap.servicesfactories.ActivityServiceTypes;
import edu.najah.cap.servicesfactories.UserServiceFactory;
import edu.najah.cap.servicesfactories.UserServiceTypes;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;

import static edu.najah.cap.logs.MyLogging.log;

public class ExportActivity implements IExportService {
    @Override
    public String exportData(String user, String path) throws Exception {
        log(Level.INFO, "Exporting activity to local storage...", "ExportActivity", "exportActivity");
        String outputPath = path + user + "-Activity.txt";

        try (PrintWriter activityWriter = new PrintWriter(new FileWriter(outputPath))) {
            IUserActivityService userActivityService = ActivityServiceFactory.getActivityService(ActivityServiceTypes.ACTIVITY_SERVICE);
            IUserService userService = UserServiceFactory.getUserService(UserServiceTypes.USER_SERVICE);
            List<UserActivity> userActivities = null;
            UserType userType = null;
            while(true){
                try {
                    userType = userService.getUser(user).getUserType();
                    userActivities = userActivityService.getUserActivity(user);
                    break;
                } catch (SystemBusyException e) {
                    log(Level.WARNING, "System Busy, Trying Again...","ExportActivity", "exportActivity");
                } catch (NotFoundException e) {
                    if(userType == null){
                        throw new NotFoundException(e.getMessage());
                    }
                    throw new UnqualifiedUserException("User is not qualified for exporting activity");
                }
            }


            for (UserActivity activity : userActivities) {
                activityWriter.println("User ID: " + activity.getUserId());
                activityWriter.println("Activity Type: " + activity.getActivityType());
                activityWriter.println("Activity Date: " + activity.getActivityDate());
                activityWriter.println();
            }
            activityWriter.close();
            return PDFConvertor.ConvertTOPdf(outputPath);
        } catch (UnqualifiedUserException e) {
            log(Level.WARNING, e.getMessage(), "ExportActivity", "exportActivity");
            try {
                File file = new File(outputPath);
                if (!file.delete()) {
                    throw new FileDeletionException("File Deletion Failed");
                }
            } catch (FileDeletionException x) {
                log(Level.SEVERE, x.getMessage(), "PDFConvertor", "ConvertTOPdf");
            }
            return null;
        }
    }
}
