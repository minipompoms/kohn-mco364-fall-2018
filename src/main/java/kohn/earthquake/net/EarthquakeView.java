package kohn.earthquake.net;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import kohn.earthquake.Earthquake;
import kohn.earthquake.EarthquakeProperties;

import static java.util.stream.Collectors.toCollection;


@SuppressWarnings("serial")
@Singleton
public class EarthquakeView extends JFrame {

	private JTextArea earthquakes;

    public EarthquakeView() {

		setLocation(540, 320);
		setSize(540, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Earthquake Feed...");

        JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        earthquakes = new JTextArea();
        pane.add(earthquakes);

		Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        add(pane);
        pane.setBorder(border);

		
	}


	public static void main(String args[]) {
		Injector injector = Guice.createInjector(new EarthquakeModule());
		EarthquakeView view = injector.getInstance(EarthquakeView.class);
				
		EarthquakeController controller = injector.getInstance(EarthquakeController.class);

		controller.refreshData();
		
		view.setVisible(true);

	}

    public void setEarthquakes(List<Earthquake> earthquakeData) {
        StringBuilder sb = new StringBuilder();

        for (int i=0; i < earthquakeData.size(); i++) {
                EarthquakeProperties properties = earthquakeData.get(i).getProperties();
                String eq = " " + properties.getMag() + "\t" + properties.getPlace();
                sb.append(eq).append("\n\n");
        }
        earthquakes.setText(sb.toString());

    }



}
