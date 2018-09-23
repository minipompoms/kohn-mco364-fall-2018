package kohn.rx.votesmart;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

@SuppressWarnings("serial")

public class VoteSmartView extends JFrame  {



	private JTextArea billData;
	private JTextArea states;
	private JLabel fields[] = new JLabel[10];

	@Inject
	public VoteSmartView(VoteSmartController controller) {

		setLocation(240, 80);
		setSize(600, 420);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Voting Data...");
		Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);

	/*	JPanel statePanel = new JPanel();
		statePanel.setLayout(new BoxLayout(statePanel, BoxLayout.Y_AXIS));
		statePanel.setBorder(border);

		for (int i=0; i<fields.length; i++) {
			fields[i] = new JLabel();
			statePanel.add(fields[i]);
		}

		add(statePanel);
		controller.loadStates();*/

	JPanel panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	panel.setBorder(border);
	billData = new JTextArea();
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JLabel();
			panel.add(fields[i]);
		}

	add(panel);
	controller.refreshElections();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				controller.stop();
			}
		});

	}


	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new VoteSmartModule());
		VoteSmartView view = injector.getInstance(VoteSmartView.class);
		view.setVisible(true);
	}


	public void setStates(List<InternalList.State> list) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size() & i < fields.length; i++) {
			String properties = ("\n"+i+". "+list.get(i).getStateId() + " - "+list.get(i).getName() );
			//String info = String.format("%n\t%-8s|%40s%n", properties);
			sb.append(properties);
			System.out.println(sb.toString());
			fields[i].setText(properties);
			break;
		}
	//states.setText(sb.toString());
		System.out.println(sb.toString());

	}
	public void setBills(List<Bill> bills) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bills.size(); i++) {
			Bill properties = bills.get(i);

			String billInfo = String.format("%n\t%-8s|%40s%n", properties.getBillNumber());
			sb.append(billInfo);

		}
	//billData.setText(sb.toString());
	}
	public void setElections(List<Elections.Election> list) {
		fields[0].setText(list.get(0).getElectionId()+ "   "+ list.get(0).getName());
		/*StringBuilder sb = new StringBuilder();
		for (int i = 1; i < list.size() & i < fields.length; i++) {
			String properties = ("\n"+i+". "+list.get(i).getElectionId());
			//String info = String.format("%n\t%-8s|%40s%n", properties);
			sb.append(properties);
			System.out.println(sb.toString());
			fields[i].setText(properties + "#");

		}
		System.out.println(sb.toString());
*/
	}
}

