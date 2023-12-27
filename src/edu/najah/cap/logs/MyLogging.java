package edu.najah.cap.logs;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogging {
    private static Logger logger;
    private static FileHandler fh;

    static {
        try {
            logger = Logger.getLogger(MyLogging.class.getName());
            fh = new FileHandler("./src/edu/najah/cap/logs/Logs.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
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
