package edu.najah.cap.export;

public class ExportFactory {
    public static IExport getExportType(ExportType type) {
        if (type == null) {
            return null;
        }
        if (type.equals(ExportType.LOCALLY)) {
            return new ExportDownload();
        } else if (type.equals(ExportType.UPLOAD)) {
            return new ExportUpload();
        }
        return null;
    }
}
