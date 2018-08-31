package kohn.earthquake;

public class EarthquakeProperties {

	private double mag;
	private String place;
	private long time;
	
	public EarthquakeProperties(double mag, String place, long time) {
		this.mag = mag;
		this.place = place;
		this.time = time;
	}
	public double getMag() {
		return mag;
	}
	public String getPlace() {
		return place;
	}
	public long getTime() {
		return time;
	}
	
	
}
