package elements;

import java.util.ArrayList;

/**
 * Created by Dan Figueras on 19/03/2016.
 * Belongs to project routecalculator.
 * All rights reserved.
 */
public class Segment {
    private double idSegment;
    private ArrayList<routePoint> routePoints;
    private routePoint startPoint;
    private routePoint endPoint;

    public double getIdSegment() {
        return idSegment;
    }

    public ArrayList<routePoint> getRoutePoints() {
        return routePoints;
    }

    public routePoint getStartPoint() {
        return startPoint;
    }

    public routePoint getEndPoint() {
        return endPoint;
    }

    public void setStartPoint(routePoint startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(routePoint endPoint) {
        this.endPoint = endPoint;
    }


}
