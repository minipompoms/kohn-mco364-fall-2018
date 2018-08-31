package kohn.earthquake.net;

import java.util.Comparator;
import java.util.Optional;

import javax.swing.text.JTextComponent;

import com.google.inject.Inject;

import kohn.earthquake.Earthquake;
import kohn.earthquake.EarthquakeFeedModel;
import kohn.earthquake.EarthquakeProperties;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EarthquakeController {
	private EarthquakeView view;
	private USGSEarthquakeService service;
	
	
	String month = "all_month.geojson";
	String day = "all_day.geojson";
	String week = "all_week.geojson";
	String hour = "all_hour.geojson";
	
	@Inject
	public EarthquakeController(EarthquakeView view, USGSEarthquakeService service) {
		this.view = view;
		this.service = service;
	}


	public void refreshData() {
		requestHour();	
		requestDay();
		requestWeek();
		requestMonth();
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

		Optional<Earthquake> largest = feed.getFeatures()
				.stream() //put this in earthquakefeed
				.max(Comparator.comparing(e -> e.getProperties().getMag()));
		
		EarthquakeProperties properties = largest.get().getProperties();
		
		String largestEQ = String.valueOf(properties.getMag()+" "+properties.getPlace());
		largestField.setText(largestEQ);
	}
	
	public void requestHour()
	{
		requestEarthquakeFeed(service.getData(hour),
				view.getLastHourTextField());
	}
	public void requestDay()
	{
		requestEarthquakeFeed(service.getData(day),
				view.getLastDayTextField());
	}
	
	public void requestWeek()
	{
		requestEarthquakeFeed(service.getData(week),
				view.getLastWeekTextField());
	}
	public void requestMonth()
	{
		requestEarthquakeFeed(service.getData(month),
				view.getLastMonthTextField());
	}
	
}
