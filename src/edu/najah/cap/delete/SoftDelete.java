package edu.najah.cap.delete;

import edu.najah.cap.exceptions.BadRequestException;

import java.util.logging.Level;

import static edu.najah.cap.delete.DeleteService.deleteData;
import static edu.najah.cap.logs.MyLogging.log;

public class SoftDelete implements IDelete {
    @Override
    public void delete(String username) throws BadRequestException {
        log(Level.INFO, "Soft Delete Started for " + username, "SoftDelete", "delete");
        deleteData("Posts", username);
        deleteData("Transactions", username);
        deleteData("User Activities", username);
        log(Level.INFO, "Soft Delete Completed for " + username, "SoftDelete", "delete");
    }
}
