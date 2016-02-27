package elements;

import helpers.distanceCalculator;

public class routeTrack {
	private routePoint[] routePoints;
	private String routeName;
	private double totalAscension=0;
	private double totalDescension=0;
	private double  totalLength = 0;
	private int totalPoints = 0;
	
	
	public routeTrack() {
		//nothing
	}

	public boolean addPoint(routePoint newRoutePoint){
		this.routePoints[this.totalPoints] = newRoutePoint;
		if(routePoints.length > 0){
			distanceCalculator.calculateDistance2Points(this.routePoints[this.totalPoints-1], this.routePoints[this.totalPoints]);
		}
		return false;
	}
	
	
}
