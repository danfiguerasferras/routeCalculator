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

    /* Status is a variable that we will use to control the segment check:
        0: Nothing declared yet, no value
        1: This point is not in any other segment
        2: This point is in another segment
     */
    private int status = 0;
    private static int NOTDECLARED = 0;
    private static int NOTCOMMON = 1;
    private static int COMMON = 2;
    private int maxDistanceBetweenPoints = 5;
    private int minimumDistancePerSegment = 10;
    private routeSegment currentRouteSegment;
    private routeSegment matchedSegment;
    private routePoint matchedPoint;
    private routePoint origenMatchedPoint;




    public segmentCreator() {
        this.routeSegments = new ArrayList<routeSegment>();
    }

    public segmentCreator(ArrayList<routeSegment> routeSegments) {
        this.routeSegments = routeSegments;
    }

    /**
     * Loads and converts a route into segments
     *
     * @param rt roadTrack you want to convert in segments
     */
    public void trateRouteTrack(routeTrack rt) {
        routePoint rp;
        int prevStatus = 0;
        // 1
        for (int i = 0; i < rt.getTotalPoints(); i++) {
            rp = rt.getPointPerPosition(i);
            this.status = this.checkRoutePoint(rp);
            // 1.1 - Not declared before
            if (prevStatus == 0) {
                this.currentRouteSegment = new routeSegment();
            }
            // 1.2
            if (this.status == prevStatus) {
                // 1.2.1
                if (this.status == 1) {
                    this.currentRouteSegment.addPoint(rp);
                // 1.2.2
                } else if (this.status == 2) {
                    // TODO
                // 1.2.3
                } else {
                    myLogger.error("The point " + rp.toString() + " got into trateRouteTrack -> option Status != PrevStatus + Status != 1&2");
                }
            // 1.3
            } else if (this.status != prevStatus && prevStatus != 0) {
                // 1.3.1
                if(prevStatus == 1){
                    // 1.3.1.1
                    if(distanceCalculator.calculateDistance2Points(this.currentRouteSegment.getPoint(this.currentRouteSegment.getRoutePointsSize()), rp) > minimumDistancePerSegment){
                        this.currentRouteSegment.addPoint(this.matchedPoint);
                        this.currentRouteSegment.setStartPoint(this.currentRouteSegment.getPoint(0));
                        this.currentRouteSegment.setEndPoint(this.matchedPoint);
                        this.routeSegments.add(this.currentRouteSegment);
                        this.divideCorrespondingSegment(prevStatus);
                        this.currentRouteSegment = new routeSegment();
                    }
                // 1.3.2
                }else if(prevStatus == 2){
                    // // TODO: 02/04/2016
                // 1.3.3
                }else{
                    myLogger.error("The status = "+this.status+", prevStatus = "+prevStatus+" and this got to segmentCreator trateRouteTrack 1.3.3");
                }
            // 1.4.1
            }else {
                myLogger.error("The point "+rp.toString()+" got into last else in trateRouteTrak (status ! equals ! different)");
            }
            prevStatus = this.status;
        }
    }

    /**
     * Checks in all the segments inside the array if the point is in there
     *
     * @param rp the route point we want to check
     * @return a parameter depending on
     */
    private int checkRoutePoint(routePoint rp) {
        routeSegment rs;
        routePoint segmentRoutePoint;
        // 1 - Check segments in the array
        for (int i = 0; i < routeSegments.size(); i++) {
            rs = routeSegments.get(i);
            ArrayList<routePoint> auxRoutePoints = rs.getRoutePoints();
            // 1.1 - For every point in the route points array for each segment
            for (int j = 0; j < auxRoutePoints.size(); j++) {
                segmentRoutePoint = auxRoutePoints.get(j);
                // 1.1.1 - If the distance is less than 10m
                if (distanceCalculator.calculateDistance2Points(rp, segmentRoutePoint) < maxDistanceBetweenPoints) {
                    // 1.1.1.1 - If this is the status change we store the first value
                    if(this.status != this.COMMON){
                        this.origenMatchedPoint = segmentRoutePoint;
                        this.matchedPoint = segmentRoutePoint;
                        this.matchedSegment = rs;
                    }
                    this.status = this.COMMON;
                // 1.1.2 - The point is not in common with the one in the segment
                } else {
                    this.status = this.NOTCOMMON;
                }
            }
        }
        myLogger.record(myLogger.WARNING, "There's an unexpected response for segmentCreator checkRoutePoint with values Point: "+rp.toString());
        return this.NOTDECLARED;
    }

    private void divideCorrespondingSegment (int prevStatus){
        if(prevStatus == 1){
            ArrayList<routePoint> al = this.currentRouteSegment.getRoutePoints();
            routeSegment rs1 = new routeSegment();
            routeSegment rs2 = new routeSegment();
            for (int i=0; i<al.indexOf(this.matchedPoint);i++){
                // TODO: 03/04/2016 create the segment 1 with the point before the  matched point and the ones after the matched point. Take minimum distance per segment into consideration.  
            }
        }

    }
}
