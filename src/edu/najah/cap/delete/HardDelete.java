package edu.najah.cap.delete;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.logs.MyLogging;

import java.util.logging.Level;

import static edu.najah.cap.delete.DeleteService.deleteData;
import static edu.najah.cap.delete.DeleteService.deleteUser;

public class HardDelete implements IDelete {
    @Override
    public void delete(String username) throws BadRequestException {
        MyLogging.log(Level.INFO, "Hard Delete Started for " + username);
        deleteData("Posts", username);
        deleteData("Transactions", username);
        deleteData("User Activities", username);
        deleteUser(username);
        MyLogging.log(Level.INFO, "Hard Delete Completed for " + username);
    }
}
