package edu.najah.cap.delete;

import edu.najah.cap.exceptions.BadRequestException;
import edu.najah.cap.logs.MyLogging;

import java.util.logging.Level;

import static edu.najah.cap.delete.DeleteService.deleteData;

public class SoftDelete implements IDelete {
    @Override
    public void delete(String username) throws BadRequestException {
        MyLogging.log(Level.INFO, "Soft Delete Started for " + username);
        deleteData("Posts", username);
        deleteData("Transactions", username);
        deleteData("User Activities", username);
        MyLogging.log(Level.INFO, "Soft Delete Completed for " + username);
    }
}
