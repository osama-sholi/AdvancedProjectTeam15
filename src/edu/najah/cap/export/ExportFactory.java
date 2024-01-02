package edu.najah.cap.export;

import edu.najah.cap.export.exportservices.ExportActivity;
import edu.najah.cap.export.exportservices.ExportPosts;
import edu.najah.cap.export.exportservices.ExportProfile;
import edu.najah.cap.export.exportservices.ExportTransaction;

public class ExportFactory {
    public static IExport getExportType(ExportType type) {
    if (type.equals(ExportType.LOCALLY)){
        return new ExportDownload();
    } else if (type.equals(ExportType.UPLOAD)) {
        return new ExportUpload();
    }
    return null;
    }
}
