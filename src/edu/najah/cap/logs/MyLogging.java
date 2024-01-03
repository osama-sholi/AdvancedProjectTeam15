package edu.najah.cap.logs;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogging {
    private static Logger logger;
    private static FileHandler fileHandler;

    static {
        try {
            logger = Logger.getLogger(MyLogging.class.getName());
            fileHandler = new FileHandler("./out/production/AdvancedProjectTeam15/edu/najah/cap/logs/logs.log");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    private static Logger getLogger() {
        return logger;
    }

    public static void log(Level level, String msg, String className, String methodName) {
        getLogger().logp(level, className, methodName, msg);
    }
}
