package main;

import elements.routeTrack;
import helpers.fileTransformator;
import helpers.myLogger;
import jdk.internal.cmm.SystemResourcePressureImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.concurrent.SynchronousQueue;

public class main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Test methods
        //Utests.runTests();
        //myLogger.record(myLogger.FATAL, "Just kidding, all goes good!");
        myLogger.setLogLevel(myLogger.WARNING);
        ArrayList<routeTrack> rts = new ArrayList<>();
        ArrayList<String> routeTitles = new ArrayList<>();
        routeTitles.add("de-masquefa-als-hostalets-de-pierola.gpx");
        routeTitles.add("Sant Sadurní - Hostalets de Pierola.gpx");
        routeTitles.add("testGPX.gpx");

        Iterator it = routeTitles.iterator();
        while (it.hasNext()) {
            String title = (String) it.next();
            //routeTrack rt = fileTransformator.readXMLFile(title);
            System.out.println(title);
        }
    }

}
