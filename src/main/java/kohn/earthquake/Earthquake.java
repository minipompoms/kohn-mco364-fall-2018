package kohn.earthquake;


public class Earthquake {

	private String id;
	private EarthquakeProperties properties;
	
	public Earthquake(double mag, String place, long time) {
		properties = new EarthquakeProperties(mag,place,time);
	}
	
	public String getId()
	{
		return id;
	}

	public EarthquakeProperties getProperties()
	{
		return properties;
	}

	public double getMagnitude()
	{
		return properties.getMag();
	}
}
