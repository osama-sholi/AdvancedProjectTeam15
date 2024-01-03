package edu.najah.cap.export.exportservices;

public class ExportServiceFactory {
    public static IExportService getExportService(ExportServiceType type) {
        if (type.equals(ExportServiceType.POST)) {
            return new ExportPost();
        } else if (type.equals(ExportServiceType.ACTIVITY)) {
            return new ExportActivity();
        } else if (type.equals(ExportServiceType.PROFILE)) {
            return new ExportProfile();
        } else if (type.equals(ExportServiceType.TRANSACTION)) {
            return new ExportTransaction();
        }
        return null;
    }
}
