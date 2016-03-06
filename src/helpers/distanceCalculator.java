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
		try{
			lat1 = pOrigin.getLat();
			lng1 = pOrigin.getLon();
			lat2 = pDestiny.getLat();
			lng2 = pDestiny.getLon();
		}catch (Exception e) {
			// Record the exception in the log
			myLogger.error(e);
		}
		
		// Making all the math 
		try{
			//Mona's ass
		    double earthRadius = itemValues.earthDiameterMetters; //meters
		    double dLat = Math.toRadians(lat2-lat1);
		    double dLng = Math.toRadians(lng2-lng1);
		    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
		               Math.sin(dLng/2) * Math.sin(dLng/2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		    dist = (earthRadius * c);
		}catch (Exception e) {
			// Can't make the math
			myLogger.error(e);
		}
	    return dist;
	}
}
