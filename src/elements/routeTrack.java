package elements;

import helpers.distanceCalculator;
import helpers.myLogger;

import java.util.ArrayList;

public class routeTrack {
	private ArrayList<routePoint> routePoints;
	private String routeName;
	private double totalAscension=0;
	private double totalDescension=0;
	private double totalLength = 0;
	private int totalPoints = 0;
	
	
	public routeTrack() {
		//nothing
	}
	
	public routeTrack(String name) {
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
	 * @return the totalDescension
	 */
	public double getTotalDescension() {
		return totalDescension;
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
		return totalPoints;
	}

	public boolean addPoint(routePoint newRoutePoint){
		try{
			// Add the point to the array
			this.routePoints.add(newRoutePoint);
			totalPoints += 1;
			// If there's a previous point
			if(routePoints.size() > 0){
				// calculate the distance with the last point and put it in the total
				routePoint rp = routePoints.get(routePoints.size()-1);
				double distance = distanceCalculator.calculateDistance2Points(rp, newRoutePoint);
				this.totalLength += distance;
				// Calculate the altitud difference between this point and the previous one
				double altitudeDifference = rp.getEle() - newRoutePoint.getEle();
				// Put it in the total
				if(altitudeDifference > 0){
					this.totalAscension += altitudeDifference;
				}else{
					this.totalDescension += altitudeDifference;
				}
			}
			//everything went good
			return true;
		}catch (Exception e) {
			myLogger.error(e);
			return false;
		}
	}
}
