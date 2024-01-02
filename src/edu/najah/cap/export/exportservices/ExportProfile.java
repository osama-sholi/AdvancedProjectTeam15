package edu.najah.cap.export.exportservices;

import edu.najah.cap.compress.PDFConvertor;
import edu.najah.cap.iam.IUserService;
import edu.najah.cap.iam.UserProfile;
import edu.najah.cap.servicesfactories.UserServiceFactory;
import edu.najah.cap.servicesfactories.UserServiceTypes;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Level;

import static edu.najah.cap.logs.MyLogging.log;

public class ExportProfile implements IExportService {
    @Override
    public String exportData(String user, String path) throws Exception {
        log(Level.INFO, "Exporting profile to local storage...", "ExportProfile", "exportProfile");
        String outputPath = path + user + "-Profile.txt";
        try (PrintWriter profileWriter = new PrintWriter(new FileWriter(outputPath))) {
            IUserService userService = UserServiceFactory.getUserService(UserServiceTypes.USER_SERVICE);
            UserProfile userProfile = null;
            while(true){
                try {
                    userProfile = userService.getUser(user);
                    break;
                } catch (Exception e) {
                    log(Level.WARNING, "System Busy, Trying Again...","ExportProfile", "exportProfile");
                }
            }
            profileWriter.println("First Name: " + userProfile.getFirstName());
            profileWriter.println("Last Name: " + userProfile.getLastName());
            profileWriter.println("Phone Number: " + userProfile.getPhoneNumber());
            profileWriter.println("Email: " + userProfile.getEmail());
            profileWriter.println("UserName: " + userProfile.getUserName());
            profileWriter.println("Password: " + userProfile.getPassword());
            profileWriter.println("Role: " + userProfile.getRole());
            profileWriter.println("Department: " + userProfile.getDepartment());
            profileWriter.println("Organization: " + userProfile.getOrganization());
            profileWriter.println("Country: " + userProfile.getCountry());
            profileWriter.println("City: " + userProfile.getCity());
            profileWriter.println("Street: " + userProfile.getStreet());
            profileWriter.println("Postal Code: " + userProfile.getPostalCode());
            profileWriter.println("Building: " + userProfile.getBuilding());
            profileWriter.close();
            return PDFConvertor.ConvertTOPdf(outputPath);
        }
    }
}
