package helpers;

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
	static String logRoute = "";
	static PrintWriter pw = null;
	static Date now = null;
	static BufferedWriter bw;
	static String todayString;
	static String logHourString;
	static File logFile;

	
	public static void record(int type, String message){
		myLogger.getLogFilePath();
		myLogger.setLogDates();
		// We check if the file exist
		try {
			createLogFile();
			// We create the buffer to be able to write (the boolean in FileWriter is to avoid overwriting)
			String finalMessage = " - DEFCON" + type + " - " + message + "\n";
			writeFile(finalMessage);
		} catch (Exception e) {
			System.out.println(logHourString + " - DEFCON1 - The log is crashing O.O look! -> "+e.getMessage());
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

	private static void createLogFile(){
		String todayRoute = logRoute+todayString+".log";
		logFile = new File(todayRoute);
		// Just wanted to leave a message
		if(!logFile.exists()) {
			System.out.println("The file "+todayString+" has been created");
		}else{
			System.out.println("The file "+todayString+" already exists");
		}
	}

	private static void setLogDates(){
		// Create the text with today's day to add it in the route (we want a log file per day)
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat logHour = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
		now = new Date();
		todayString = dateFormat.format(now);
		logHourString = logHour.format(now);
	}

	private static void getLogFilePath(){
		if(myLogger.logRoute==""){
			myLogger.logRoute=itemValues.getLogRoute();
		}
	}

	private static void writeFile(String text){
		try{
			bw = new BufferedWriter(new FileWriter(logFile, true));
			bw.write(myLogger.logHourString + text);
		}catch (Exception e){
			System.out.println(logHourString + " - DEFCON1 - The log is crashing O.O look! -> "+e.getMessage());
		}
	}
	
	public static void error(Exception e){
		record(ERROR, e.getMessage());
	}
}