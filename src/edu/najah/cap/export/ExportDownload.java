package edu.najah.cap.export;

import edu.najah.cap.compress.CompressFactory;
import edu.najah.cap.compress.CompressType;
import edu.najah.cap.export.exportservices.ExportServiceFactory;
import edu.najah.cap.export.exportservices.ExportServiceType;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static edu.najah.cap.logs.MyLogging.log;

public class ExportDownload implements IExport{
    public void exportData(String username, String path) {
        log(Level.INFO, "Exporting " + username + " data to " + path, "ExportDownload", "exportData");
        try {
            List<String> files = new ArrayList<>();

            files.add(ExportServiceFactory.getExportService(ExportServiceType.POST).exportData(username, path));
            files.add(ExportServiceFactory.getExportService(ExportServiceType.ACTIVITY).exportData(username, path));
            files.add(ExportServiceFactory.getExportService(ExportServiceType.PROFILE).exportData(username, path));
            files.add(ExportServiceFactory.getExportService(ExportServiceType.TRANSACTION).exportData(username, path));

            CompressFactory.getCompressType(CompressType.ZIP).compress(path, files);
        } catch (FileNotFoundException e) {
            log(Level.SEVERE, e.getMessage(), "ExportDownload", "exportData");
        } catch (Exception e) {
            log(Level.SEVERE, e.getMessage(), "ExportDownload", "exportData");
        }
    }
}
