package kohn.earthquake.net;

import com.google.inject.AbstractModule;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class EarthquakeModule extends AbstractModule {

	@Override
	protected void configure() {
		super.configure();
	
	
	Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("https://earthquake.usgs.gov")
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())//takes calladapterfactory and changes into retrofit
			.build();
	
	USGSEarthquakeService service = retrofit.create(USGSEarthquakeService.class);

	bind(USGSEarthquakeService.class).toInstance(service);
}
}
