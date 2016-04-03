package elements;

import java.util.ArrayList;

/**
 * Created by Dan Figueras on 19/03/2016.
 * Belongs to project routecalculator.
 * All rights reserved.
 */
public class routeSegment {
    private double idSegment;
    private ArrayList<routePoint> routePoints;
    private routePoint startPoint;
    private routePoint endPoint;

    public routeSegment() {
        // Nothing
    }

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

    public void addPoint(routePoint rp) {
        this.routePoints.add(rp);
    }

    public routePoint getPoint (int index){
        return this.routePoints.get(index);
    }

    public int getRoutePointsSize(){
        return this.routePoints.size();
    }
}