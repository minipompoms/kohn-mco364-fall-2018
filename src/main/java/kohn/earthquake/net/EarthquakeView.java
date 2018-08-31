package kohn.earthquake.net;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import kohn.earthquake.EarthquakeFeedModel;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("serial")
@Singleton
public class EarthquakeView extends JFrame {
	
	
	private JTextField lastHour;
	private JTextField lastMonth;
	private JTextField lastWeek;
	private JTextField lastDay;


	String month = "month";
	String day = "day";
	String week = "week";
	String hour = "hour";

	Call<EarthquakeFeedModel>call;

	public EarthquakeView() {
		setLocation(540, 320);
		setSize(540, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel labelPane = new JPanel(new GridLayout(0, 1));
		labelPane.add(new JLabel("Last Hour:"));
		labelPane.add(new JLabel("Last 24 Hours:"));
		labelPane.add(new JLabel("Last Week:"));
		labelPane.add(new JLabel("Last Month:"));

		JPanel magPane = new JPanel(new GridLayout(0, 1));
		lastHour = new JTextField();
		lastDay = new JTextField();
		lastWeek = new JTextField();
		lastMonth = new JTextField();

		magPane.add(lastHour);
		magPane.add(lastDay);
		magPane.add(lastWeek);
		magPane.add(lastMonth);
		lastMonth.setColumns(28);

		Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		setTitle("Earthquake Feed");
		labelPane.setBorder(border);
		magPane.setBorder(border);

		add(labelPane, BorderLayout.LINE_START);

		add(magPane, BorderLayout.LINE_END);
		
	}

	
	
	public JTextComponent getLastHourTextField() {
		return lastHour;
	}
	
	public JTextComponent getLastDayTextField() {
		return lastDay;
	}
	
	public JTextComponent getLastWeekTextField() {		
		return lastWeek;
	}

	public JTextComponent getLastMonthTextField() {
		return lastMonth;
	}
	
	

	public static void main(String args[]) {
		Injector injector = Guice.createInjector(new EarthquakeModule());
		EarthquakeView view = injector.getInstance(EarthquakeView.class);
				
		EarthquakeController controller = injector.getInstance(EarthquakeController.class);

		controller.refreshData();
		
		view.setVisible(true);

	}

	
}
