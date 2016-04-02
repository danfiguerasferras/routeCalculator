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
        }
    }

    private int checkRoutePoint(routePoint rp){
        routeSegment rs = new routeSegment();
        for (int i = 0; i < routeSegments.size();i++){
            rs = routeSegments.get(i);
            // // TODO: 02/04/2016 finish the bucle
            //for (int j = 0;;)
        }
        return 0;
    }
}
