package kohn.earthquake.net;


import kohn.earthquake.EarthquakeFeedModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface USGSEarthquakeService {
	
	
	@GET("/earthquakes/feed/v1.0/summary/all_{range}.geojson")
	Call<EarthquakeFeedModel> getData(@Path("range") String range);
	
	
	

	
	
}
