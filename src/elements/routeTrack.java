package elements;

import helpers.distanceCalculator;
import helpers.myLogger;

import java.util.ArrayList;
import java.util.Iterator;

public class routeTrack {
    private ArrayList<routePoint> routePoints;
    private String routeName;
    private double totalAscension = 0;
    private double totalDecline = 0;
    private double totalLength = 0;


    public routeTrack() {
        routePoints = new ArrayList<routePoint>();
        //nothing
    }

    public routeTrack(String name) {
        routePoints = new ArrayList<routePoint>();
        this.routeName = name;
    }

    /**
     * @return the routeName
     */
    public String getRouteName() {
        return routeName;
    }

    /**
     * @param routeName the routeName to set
     */
    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    /**
     * @return the totalAscension
     */
    public double getTotalAscension() {
        return totalAscension;
    }

    /**
     * @return the totalDecline
     */
    public double getTotalDecline() {
        return totalDecline;
    }

    /**
     * @return the totalLength
     */
    public double getTotalLength() {
        return totalLength;
    }

    /**
     * @return the totalPoints
     */
    public int getTotalPoints() {
        return routePoints.size();
    }

    @Override
    public String toString() {
        String res = "";
        try {
            int i = 0;
            String aux;
            Iterator it = this.routePoints.iterator();

            while (it.hasNext()) {
                aux = "Position " + i + ": " + it.next().toString();
                System.out.println(aux);
                res.concat(aux + "\n");
                i++;
            }
        } catch (Exception e) {
            myLogger.error(e);
        }
        return res;
    }

    public boolean addPoint(routePoint newRoutePoint) {
        try {
            myLogger.record(myLogger.DEBUG, "We are adding " + newRoutePoint.toString());
            // If there's a previous point
            if (this.routePoints.size() > 0) {
                // calculate the distance with the last point and put it in the total
                routePoint rp = this.routePoints.get(this.routePoints.size() - 1);
                double distance = distanceCalculator.calculateDistance2Points(rp, newRoutePoint);
                this.totalLength += distance;
                // Calculate the altitud difference between this point and the previous one
                double altitudeDifference = rp.getEle() - newRoutePoint.getEle();
                // Put it in the total
                if (altitudeDifference > 0) {
                    this.totalAscension += altitudeDifference;
                } else {
                    this.totalDecline += altitudeDifference;
                }
            }
            // Add the point to the array
            this.routePoints.add(newRoutePoint);
            //everything went good
            return true;
        } catch (Exception e) {
            myLogger.error(e);
            return false;
        }
    }
}
