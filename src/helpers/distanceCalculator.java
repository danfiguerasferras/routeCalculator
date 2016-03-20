package helpers;

import config.configItems;
import elements.routePoint;

public abstract class distanceCalculator {

    /**
     * @return meters of difference between two points without considering elevation
     */
    public static double calculateDistance2Points(routePoint pOrigin, routePoint pDestiny) {
        double lat1 = 0;
        double lng1 = 0;
        double lat2 = 0;
        double lng2 = 0;
        double dist = 0;
        double distWithElevation = 0;
        try {
            lat1 = pOrigin.getLat();
            lng1 = pOrigin.getLon();
            lat2 = pDestiny.getLat();
            lng2 = pDestiny.getLon();
        } catch (Exception e) {
            // Record the exception in the log
            myLogger.error(e);
        }

        // Making all the math
        try {
            //Calculate the distance taking the earth radius into consideration
            double earthRadius = itemValues.earthDiameterMetters; //meters
            if (pOrigin.getEle() != 0) {
                earthRadius += pOrigin.getEle();
            }
            double dLat = Math.toRadians(lat2 - lat1);
            double dLng = Math.toRadians(lng2 - lng1);
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                            Math.sin(dLng / 2) * Math.sin(dLng / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            dist = (earthRadius * c);

            //Takes elevation change into consideration
            if (pOrigin.getEle() != 0 && pDestiny.getEle() != 0) {
                myLogger.debug("Checking points with elevations: " + pOrigin.getEle() + " and " + pDestiny.getEle());
                double differenceDistance = pOrigin.getEle() - pDestiny.getEle();
                distWithElevation = getDistance2D(dist, differenceDistance);
                myLogger.debug("Result: " + differenceDistance);
            }

        } catch (Exception e) {
            // Can't make the math
            myLogger.error(e);
        }

        if (distWithElevation != 0) {
            return distWithElevation;
        } else {
            return dist;
        }
    }

    public static double stringToDouble(String inputString) {
        double value = 0;
        try {
            value = Double.parseDouble(inputString);
        } catch (Exception e) {
            myLogger.error(e);
        }
        return value;
    }

    private static double getDistance2D(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
