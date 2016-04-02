package helpers;

import elements.routePoint;
import elements.routeSegment;
import elements.routeTrack;

import java.util.ArrayList;

/**
 * Created by Dan Figueras on 02/04/2016.
 * Belongs to project routecalculator.
 * All rights reserved.
 */
public class segmentCreator {
    private ArrayList<routeSegment> routeSegments;

    /* Recording is a variable that we will use to control the segment check:
        0: Nothing declared yet, no value
        1: This point is not in any other segment
        2: This point is in another segment
     */
    private int recording = 0;
    private static int NOTDECLARED = 0;
    private static int NOTCOMMON = 1;
    private static int COMMON = 2;
    private int maxDistanceBetweenPoints = 10;

    public segmentCreator() {
        this.routeSegments = new ArrayList<routeSegment>();
    }

    public segmentCreator(ArrayList<routeSegment> routeSegments) {
        this.routeSegments = routeSegments;
    }

    /**
     * Loads and converts a route into segments
     * @param rt roadTrack you want to convert in segments
     */
    public void trateRouteTrack(routeTrack rt) {
        routePoint rp = new routePoint();
        for (int i = 0; i < rt.getTotalPoints(); i++) {
            rp = rt.getPointPerPosition(i);
            this.checkRoutePoint(rp);
            // TODO there's the code that creates the segment pending
        }
    }

    /**
     * Checks in all the segments inside the array if the point is in there
     * @param rp the route point we want to check
     * @return a parameter depending on
     */
    private int checkRoutePoint(routePoint rp){
        routeSegment rs;
        routePoint segmentRoutePoint;
        // Check all the points in the segment
        for (int i = 0; i < routeSegments.size();i++){
            rs = routeSegments.get(i);
            ArrayList<routePoint> auxRoutePoints = rs.getRoutePoints();
            for (int j = 0;j<auxRoutePoints.size();j++){
                segmentRoutePoint = auxRoutePoints.get(j);
                // If the distance is less than 10m
                if(distanceCalculator.calculateDistance2Points(rp, segmentRoutePoint) < maxDistanceBetweenPoints) {
                    this.recording = this.COMMON;
                }else{
                    this.recording=this.NOTCOMMON;
                }
            }
        }
        return this.NOTDECLARED;
    }
}
