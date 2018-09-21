package kohn.rx.votesmart;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

@SuppressWarnings("serial")

public class VoteSmartView extends JFrame  {



	private JTextArea billData;
	private JTextArea states;

	@Inject
	public VoteSmartView(VoteSmartController controller) {

		setLocation(240, 80);
		setSize(600, 420);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Voting Data...");

		JPanel statePanel = new JPanel();
		statePanel.setLayout(new BoxLayout(statePanel, BoxLayout.Y_AXIS));
		states = new JTextArea();

		statePanel.add(states);

		Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		statePanel.setBorder(border);
		add(statePanel);
		controller.loadStates();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				controller.stop();
			}
		});

	}


	public static void main(String[] args) {
		Injector injector = Guice.createInjector((Module) new VoteSmartModule());
		VoteSmartView view = injector.getInstance(VoteSmartView.class);
		view.setVisible(true);
	}


	public void setStates(List<InternalList.State> list) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 55; i++) {
			InternalList.State properties = list.get(i);
			//String info = String.format("%n\t%-8s|%40s%n", properties);
			sb.append(properties);
		}
	states.setText(sb.toString());
		System.out.println(sb.toString());

	}


}

	/*public void setData(List<Bill> bills) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bills.size(); i++) {
			Bill properties = bills.get(i);
			String billInfo = String.format("%n\t%-8s|%40s%n", properties.toString());
			sb.append(billInfo);

		}*/