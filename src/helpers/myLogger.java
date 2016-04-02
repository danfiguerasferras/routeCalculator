package helpers;

import config.configItems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class myLogger {
    // Type values
    public static int DEBUG = 5;
    public static int INFO = 4;
    public static int WARNING = 3;
    public static int ERROR = 2;
    public static int FATAL = 1;

    // Needed to log things
    private static String logRoute = "";
    private static PrintWriter pw = null;
    private static Date now = null;
    private static BufferedWriter bw;
    private static String todayString;
    private static String logHourString;
    private static File logFile;
    private static int logLevel = 3;

    public static int getLogLevel() {
        return logLevel;
    }

    public static void setLogLevel(int logLevel) {
        if(logLevel > 5)
        {
            myLogger.logLevel = 5;
        }else if(logLevel < 0){
            myLogger.logLevel = 1;
        }else{
            myLogger.logLevel = logLevel;
        }

    }


    public static void record(int type, String message) {
        myLogger.getLogFilePath();
        myLogger.setLogDates();
        // We check if the file exist
        try {
            createLogFile();
            // We create the buffer to be able to write (the boolean in FileWriter is to avoid overwriting)
            String finalMessage = " - DEFCON" + type + " - " + message + "\n";
            writeFile(finalMessage);
        } catch (Exception e) {
            System.out.println(logHourString + " - DEFCON1 - The log is crashing O.O look! -> " + e.getMessage());
        } finally {
            try {
                // Finally we close the file
                if (null != bw)
                    bw.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void createLogFile() {
        String todayRoute = logRoute + todayString + ".log";
        logFile = new File(todayRoute);
    }

    private static void setLogDates() {
        // Create the text with today's day to add it in the route (we want a log file per day)
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat logHour = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
        now = new Date();
        todayString = dateFormat.format(now);
        logHourString = logHour.format(now);
    }

    private static void getLogFilePath() {
        if (myLogger.logRoute.equals("")) {
            myLogger.logRoute = itemValues.getLogRoute();
        }
    }

    private static void writeFile(String text) {
        try {
            bw = new BufferedWriter(new FileWriter(logFile, true));
            bw.write(myLogger.logHourString + text);
        } catch (Exception e) {
            System.out.println(logHourString + " - DEFCON1 - The log is crashing O.O look! -> " + e.getMessage());
        }
    }

    public static void error(Exception e) {
        record(ERROR, e.getMessage());
    }

    public static void error(String s) {
        record(ERROR, s);
    }

    public static void debug(String s) {
        myLogger.record(myLogger.DEBUG, s);
        if (configItems.isLoc()) {
            System.out.println(s);
        }
    }
}