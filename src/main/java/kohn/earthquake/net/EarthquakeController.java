package kohn.earthquake.net;

import java.util.*;
import javax.swing.text.JTextComponent;

import com.google.inject.Inject;

import com.google.inject.Provider;
import kohn.earthquake.Earthquake;
import kohn.earthquake.EarthquakeFeedModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.stream.Collectors;

public class EarthquakeController {
	private USGSEarthquakeService service;

	private Provider<EarthquakeView> viewProvider;
	String month = "month";
	String day = "day";
	String week = "week";
	String hour = "hour";
	
	@Inject
	public EarthquakeController(USGSEarthquakeService service, Provider<EarthquakeView> viewProvider) {
		this.service = service;
		this.viewProvider = viewProvider;
	}
	public void refreshData() {
		service.getData(day).enqueue(new Callback<EarthquakeFeedModel>() {
			@Override
			public void onResponse(Call<EarthquakeFeedModel> call, Response<EarthquakeFeedModel> response) {
				EarthquakeFeedModel feed = response.body();

				List<Earthquake> earthquakes = feed.getFeatures()
						.stream()
						.filter(earthquake -> earthquake.getProperties().getMag() >= 1.1)
						.sorted(Comparator.comparing(Earthquake::getMagnitude).reversed())
						.limit(5)
						.collect(Collectors.toList());

				viewProvider.get().setEarthquakes(earthquakes);

			}

			@Override
			public void onFailure(Call<EarthquakeFeedModel> call, Throwable t) {
				t.printStackTrace();
			}
		});
	}





	private void requestEarthquakeFeed(Call<EarthquakeFeedModel>call,
			JTextComponent largestField) {	
		
		call.enqueue(new Callback<EarthquakeFeedModel>() {

			@Override
			public void onResponse(Call<EarthquakeFeedModel> call, Response<EarthquakeFeedModel> response) {
				EarthquakeFeedModel feed = response.body();
				showLargestEarthquake(largestField, feed);				
			}
			@Override
			public void onFailure(Call<EarthquakeFeedModel> call, Throwable t) {
				t.printStackTrace();
			}
		});
	}

	public void showLargestEarthquake(JTextComponent largestField, EarthquakeFeedModel feed) {

		List<Earthquake> largest = feed.getFeatures()
				.stream()
				.filter(earthquake -> earthquake.getProperties().getMag() >= 1.1)
				.sorted(Comparator.comparing(Earthquake::getMagnitude).reversed())
				.limit(5)
				.collect(Collectors.toList());

		viewProvider.get().setEarthquakes(largest);

		largestField.setText(String.valueOf(largest.get(4).getMagnitude()) + ", ");
	}



}
