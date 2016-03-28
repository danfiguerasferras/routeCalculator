package helpers;

import config.configItems;

/**
 * Created by Dan Figueras on 06/03/2016.
 * Belongs to project routecalculator.
 * All rights reserved.
 */
public abstract class itemValues {
    public static double earthDiameterMetters = 6371000;


    /**
     * @return the Log files route depending on the environment this is running.
     */
    public static String getLogRoute() {
        if (configItems.isLoc()) {
            return "C:/users/blad3r/documents/projects/java/routecalculator/logs/";
        } else {
            // As the system won't be able to create the log file, print it in the debug.
            System.out.println("We're not able to recognize the environment");
            return "";
        }
    }

    /**
     * @return the path where the routes are stored depending on the environment.
     */
    public static String getTracksRoute() {
        if (configItems.isLoc()) {
            return "C:/users/blad3r/documents/projects/java/routecalculator/sources/routes/";
        } else {
            myLogger.record(myLogger.ERROR, "We're not able to recognize the environment");
            return "";
        }
    }
}
