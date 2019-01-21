package kohn.earthquake.net;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kohn.earthquake.Earthquake;

import java.util.List;

import java.util.stream.Collectors;

public class EarthquakeController {
    private USGSEarthquakeService service;

    private Provider<EarthquakeView> viewProvider;
    private static String month = "month";
    private static String day = "day";
    private static String week = "week";
    private static String hour = "hour";
    private static Disposable disposable;

    @Inject
    public EarthquakeController(USGSEarthquakeService service, Provider<EarthquakeView> viewProvider) {
        this.service = service;
        this.viewProvider = viewProvider;
    }

    public void refreshData() { //observable simplifies cause no call object
        disposable = service.getData(day)
                        .map(feed -> feed.getFeatures())
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.single())
                        .subscribe(this::setEarthquakes, throwable -> throwable.printStackTrace());
    }

    private void setEarthquakes(List<Earthquake> list) {
        List<Earthquake> earthquakes = list
                .stream()
                .filter((earthquake -> earthquake.getProperties().getMag() >= 3.0))
                .sorted((e1, e2) -> e1.getMagnitude() > e2.getMagnitude() ? -1 : 1)
                .limit(5)
                .collect(Collectors.toList());


        viewProvider.get().setEarthquakes(earthquakes);
    }


    public void stop() {
        disposable.dispose();
    }

}
