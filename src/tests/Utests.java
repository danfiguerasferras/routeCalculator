package tests;

import java.util.ArrayList;
import java.util.List;

import elements.routePoint;

public abstract class Utests {
	
	private static List<String> result = new ArrayList<String>();
	private static routePoint rp1 = null;
	private static routePoint rp2 = null;
	
	public static boolean runTests(){
		result.add(checkRoutePointConstructor());
		printResults();
		return false;
	}
	
	// Check the creation and get methods for the routePoint
	public static String checkRoutePointConstructor(){
		try {
			rp1 = new routePoint(41.498667, 1.838284);
			rp2 = new routePoint(41.477459, 1.913167, 100);
			
			if(rp1.getLat() == 41.498667 && rp1.getLon() == 1.838284 && rp2.getLat() == 41.477459 && rp2.getLon() == 1.913167 && rp2.getEle() == 100){
				// OK
			}else{
				return "The creation of the points went good, but values are wrong";
			}
		} catch (Exception e) {
			return "The creation of the points was broken";
		}
		return "checkRoutePointConstructor OK";
	}
	
	// Print the Utests results 
	public static void printResults() {
		try {
			if(!result.isEmpty() && result.size() > 0)
			{
				for(int i=0; i<result.size(); i++){
					if(result.get(i)!=null){
						System.out.println(result.get(i));
					}
				}
			}
		} catch (Exception e) {
			// I broke something...
			System.out.println("Something went reaaaaally wrong...");
		}
		
	}
}
