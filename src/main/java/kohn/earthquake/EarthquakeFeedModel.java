package kohn.earthquake;

import java.util.List;

public class EarthquakeFeedModel {

	private String type;
	private List<Earthquake> features;
	
	public EarthquakeFeedModel(List<Earthquake> features, String type) {
		this.type = type;
		this.features = features;
	}
	

	public String getType() {
		return type;
	}

	public List<Earthquake> getFeatures(){
		return features;
	}
	
	
	
	
	
	
	
}
