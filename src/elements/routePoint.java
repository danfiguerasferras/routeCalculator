package elements;

public class routePoint {
	private double identifier;
	private double lat;
	private double lon;
	private double ele;
	
	
	public routePoint() {
		
	}
	
	public routePoint(double newLat, double newLon) {
		this.lat = newLat;
		this.lon = newLon;
	}
	
	public routePoint(double newLat, double newLon, double newEle) {
		this.lat = newLat;
		this.lon = newLon;
		this.ele = newEle;
	}
	
	public routePoint routePointWithIdentifier(double newLat, double newLon, double newIdentifier) {
		this.lat = newLat;
		this.lon = newLon;
		this.setIdentifier(newIdentifier);
		return this;
	}
	
	public routePoint routePointWithIdentifier(double newLat, double newLon, double newEle, double newIdentifier) {
		this.lat = newLat;
		this.lon = newLon;
		this.ele = newEle;
		this.setIdentifier(newIdentifier);
		return this;
	}

	@Override
    public String toString(){
        String st = "{ ";

        if(this.getLat()!=0){
            st+="Latitude: "+this.getLat()+" ";
        }

        if(this.getLon()!=0) {
            st+="Longitude: "+this.getLon()+" ";
        }

        if(this.getEle()!=0){
            st+="Elevation: "+this.getEle()+" ";
        }
        st+="}";
        return st;
    }

	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * @return the lon
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * @param lon the lon to set
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}

	/**
	 * @return the ele
	 */
	public double getEle() {
		return ele;
	}

	/**
	 * @param ele the ele to set
	 */
	public void setEle(double ele) {
		this.ele = ele;
	}

	public void setIdentifier(double identifier) {
		this.identifier = identifier;
	}

	public double getIdentifier() {
		return identifier;
	}
	
	
}
