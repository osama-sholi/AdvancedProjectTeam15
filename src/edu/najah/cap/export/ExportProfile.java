package edu.najah.cap.export;

import edu.najah.cap.iam.IUserService;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ExportProfile {
    public static void exportProfile(String user, IUserService userService) {
        try (PrintWriter profileWriter = new PrintWriter(new FileWriter(user + " Profile.txt"))) {
            profileWriter.println("First Name: " + userService.getUser(user).getFirstName());
            profileWriter.println("Last Name: " + userService.getUser(user).getLastName());
            profileWriter.println("Phone Number: " + userService.getUser(user).getPhoneNumber());
            profileWriter.println("Email: " + userService.getUser(user).getEmail());
            profileWriter.println("UserName: " + userService.getUser(user).getUserName());
            profileWriter.println("Password: " + userService.getUser(user).getPassword());
            profileWriter.println("Role: " + userService.getUser(user).getRole());
            profileWriter.println("Department: " + userService.getUser(user).getDepartment());
            profileWriter.println("Organization: " + userService.getUser(user).getOrganization());
            profileWriter.println("Country: " + userService.getUser(user).getCountry());
            profileWriter.println("City: " + userService.getUser(user).getCity());
            profileWriter.println("Street: " + userService.getUser(user).getStreet());
            profileWriter.println("Postal Code: " + userService.getUser(user).getPostalCode());
            profileWriter.println("Building: " + userService.getUser(user).getBuilding());
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
