package edu.najah.cap.export;

import java.util.logging.Level;

import static edu.najah.cap.logs.MyLogging.log;

public class ExportUpload implements IExport{
    public void exportData(String username, String link) {
        log(Level.INFO, "Exporting " + username + " data to " + link, "ExportUpload", "exportData");
    }
}
