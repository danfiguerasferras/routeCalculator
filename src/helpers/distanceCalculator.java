package helpers;

import sun.rmi.runtime.Log;
import elements.routePoint;

public abstract class distanceCalculator {
	
	/**
	 * @return metters of difference between two points without considering elevation
	 */
	public static float calculateDistance2Points(routePoint pOrigin, routePoint pDestiny) {
		float lat1 = 0;
		float lng1 = 0;
		float lat2 = 0;
		float lng2 = 0;
		float dist = 0;
		try{
			lat1 = (float) pOrigin.getLat();
			lng1 = (float) pOrigin.getLon();
			lat2 = (float) pDestiny.getLat();
			lng2 = (float) pDestiny.getLon();
		}catch (Exception e) {
			// TODO: handle exception
			//wasn't able to cast...
		}
		try{
		    double earthRadius = 6371000; //meters
		    double dLat = Math.toRadians(lat2-lat1);
		    double dLng = Math.toRadians(lng2-lng1);
		    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
		               Math.sin(dLng/2) * Math.sin(dLng/2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		    dist = (float) (earthRadius * c);
		}catch (Exception e) {
			// TODO: handle exception
			// Can't make the maths
		}
		
	    return dist;
	}
}
