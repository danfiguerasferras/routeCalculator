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
	static String logRoute = "C:/users/blad3r/documents/projects/java/routecalculator/logs/";
	static FileWriter fw = null;
	static PrintWriter pw = null;
	static Date now = null;
	static BufferedWriter bw;
	
	
	public static void record(int type, String message){
		// Create the text with today's day to add it in the route (we want a log file per day)
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat logHour = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
		now = new Date();
		String todayString = dateFormat.format(now);
		String logHourString = logHour.format(now);
		// We check if the file exist
		try {
			String todaysRoute = logRoute+todayString+".log";
			File logFile = new File(todaysRoute);
			// Just wanted to leave a message
			if(!logFile.exists()) {
				System.out.println("The file "+todayString+" has been created");
			}else{
				System.out.println("The file "+todayString+" already exists");
			}
			// We create the buffer to be able to write
			bw = new BufferedWriter(new FileWriter(logFile));
			bw.write(logHourString + " - DEFCON" + type + " - " + message + "\n");
		} catch (Exception e) {
			// TODO: handle exception
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
}