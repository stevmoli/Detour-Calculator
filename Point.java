import java.util.*;

// by Steven Molitor

public class Point {
	private Double latitude;
	private Double longitude;
	private HashMap<Point, Double> distances;

	public Point(Double lat, Double lng) {
		this.latitude = lat;
		this.longitude = lng;
		this.distances = new HashMap<Point, Double>();
	}

	public Double getLatitude(){
		return this.latitude;
	}

	public Double getLongitude(){
		return this.longitude;
	}

	public Double calculateDistance(Point p) {
		if (this.distances.containsKey(p)==true) {  // we avoid recalculating distances by storing each calculation
			return distances.get(p);
		} else {  // calculate distance using distance formula
			Double x = Math.pow((this.latitude - p.latitude), 2);
			Double y = Math.pow((this.longitude - p.longitude), 2);
			return Math.abs( Math.sqrt(x+y) );
		}
	}

	public String toString() {
		return "("+this.latitude+", "+this.longitude+")";
	}

}
