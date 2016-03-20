package tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

import elements.routePoint;
import helpers.distanceCalculator;

public abstract class Utests {

    private static List<String> result = new ArrayList<String>();
    private static routePoint rp1 = null;
    private static routePoint rp2 = null;
    private static double latExample1 = 41.498667;
    private static double lonExample1 = 1.838284;
    private static double latExample2 = 41.477459;
    private static double lonExample2 = 1.913167;
    private static double eleExample2 = 100;
    private static double distanceRp1Rp2 = 6668.321157660885;

    /**
     * This runs all the tests in the class
     */
    public static boolean runTests() {
        result.add(checkRoutePointConstructor());
        result.add(checkCalculateDistance2Points());
        printResults();
        return false;
    }

    // Check the creation and get methods for the routePoint
    public static String checkRoutePointConstructor() {
        try {
            rp1 = new routePoint(latExample1, lonExample1);
            rp2 = new routePoint(latExample2, lonExample2, eleExample2);

            if (rp1.getLat() == latExample1 && rp1.getLon() == lonExample1 && rp2.getLat() == latExample2 && rp2.getLon() == lonExample2 && rp2.getEle() == eleExample2) {
                // OK
            } else {
                return "The creation of the points went good, but values are wrong";
            }
        } catch (Exception e) {
            return "The creation of the points was broken";
        }
        return "distanceCalculator.checkRoutePointConstructor OK";
    }

    // Check distanceCalculator.calculateDistance2Points
    public static String checkCalculateDistance2Points() {
        try {
            if (rp1 == null || rp2 == null) {
                checkRoutePointConstructor();
            }
            double res = distanceCalculator.calculateDistance2Points(rp1, rp2);
            if (res == distanceRp1Rp2) {
                return "distanceCalculator.calculateDistance2Points OK";
            }
        } catch (Exception e) {
            return "The calculations on distanceCalculator.calculateDistance2Points are not working well";
        }
        return "The calculations on distanceCalculator.calculateDistance2Points result is not correct";
    }


    // Print the Utests results
    public static void printResults() {
        try {
            if (!result.isEmpty() && result.size() > 0) {
                Iterator it = result.iterator();
                while (it.hasNext()) {
                    System.out.println(it.next());
                }
            }
        } catch (Exception e) {
            // I broke something...
            System.out.println("Something went reaaaaally wrong...");
        }
    }
}
