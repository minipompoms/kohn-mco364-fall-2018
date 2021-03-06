package kohn.earthquake.net;


import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import kohn.earthquake.Earthquake;
import kohn.earthquake.EarthquakeProperties;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;



@SuppressWarnings("serial")
@Singleton

public class EarthquakeView extends JFrame {

	private JTextArea earthquakes;

    @Inject
    public EarthquakeView(EarthquakeController controller) {
        setLocation(540, 320);

		setSize(540, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Earthquake Feed...");

        JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        earthquakes = new JTextArea();
        pane.add(earthquakes);

		Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        pane.setBorder(border);
        add(pane);

        controller.refreshData();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                controller.stop();
            }
        });

	}


	public static void main(String args[]) {
		Injector injector = Guice.createInjector(new EarthquakeModule());
		EarthquakeView view = injector.getInstance(EarthquakeView.class);
		view.setVisible(true);

	}

    public void setEarthquakes(List<Earthquake> earthquakeData) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < earthquakeData.size(); i++) {
                EarthquakeProperties properties = earthquakeData.get(i).getProperties();
                String eq=String.format("%n\t%-8s|%40s%n",properties.getMag(), properties.getPlace());
                sb.append(eq);
        }
        earthquakes.setText(sb.toString());
    }
}
