package edu.najah.cap.delete;

import edu.najah.cap.exceptions.BadRequestException;

import java.util.logging.Level;

import static edu.najah.cap.delete.DeleteService.deleteData;
import static edu.najah.cap.delete.DeleteService.deleteUser;
import static edu.najah.cap.logs.MyLogging.log;

public class HardDelete implements IDelete {
    @Override
    public void delete(String username) throws BadRequestException {
        log(Level.INFO, "Hard Delete Started for " + username, "HardDelete", "delete");
        deleteData("Posts", username);
        deleteData("Transactions", username);
        deleteData("User Activities", username);
        deleteUser(username);
        log(Level.INFO, "Hard Delete Completed for " + username, "HardDelete", "delete");
    }
}
