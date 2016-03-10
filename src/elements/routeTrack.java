package elements;

import helpers.distanceCalculator;
import helpers.myLogger;

public class routeTrack {
	private routePoint[] routePoints; // // TODO: 10/03/2016 change this to arrayList of routePoints 
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

	// // TODO: 10/03/2016 Change this to be dynamic 
	public boolean addPoint(routePoint newRoutePoint){
		try{
			// Add the point to the array
			this.routePoints[this.totalPoints] = newRoutePoint;
			totalPoints += 1;
			// If there's a previous point
			if(routePoints.length > 0){
				// calculate the distance with the last point and put it in the total
				double distance = distanceCalculator.calculateDistance2Points(this.routePoints[this.totalPoints-1], this.routePoints[this.totalPoints]);
				this.totalLength += distance;
				// Calculate the altitud difference between this point and the previous one
				double altitudeDifference = this.routePoints[this.totalPoints-1].getEle() - this.routePoints[this.totalPoints].getEle();
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
