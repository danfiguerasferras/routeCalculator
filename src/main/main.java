package main;

import elements.routeTrack;
import helpers.fileTransformator;

public class main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Test methods
        //Utests.runTests();
        //myLogger.record(myLogger.FATAL, "Just kidding, all goes good!");
        routeTrack rt = fileTransformator.readXMLFile("Sant Sadurní - Hostalets de Pierola.gpx");
        System.out.println(rt.toString());
        System.out.println(rt.getTotalLength());

    }

}
