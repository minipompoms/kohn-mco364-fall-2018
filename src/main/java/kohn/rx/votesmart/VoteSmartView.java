package kohn.rx.votesmart;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import javax.inject.Singleton;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("serial")

@Singleton
public class VoteSmartView extends JFrame  {
    
	private JTextArea billData;
	private JTextArea states;
	private JLabel fields[] = new JLabel[10];
	private String stateList;
	private JTextArea elections;
	private JTextArea candidate;

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
	//states = new JTextArea(stateList);
	//panel.add(states);//	elections = new JTextArea();
	//	panel.add(elections);
		candidate = new JTextArea();
		panel.add(candidate);
		billData = new JTextArea();
		panel.add(billData);

		add(panel);
		//controller.getCandidateData();
		controller.getBills();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				controller.stop();
			}
		});

    }

	public void setCandidates(Stream<Candidate> candidates){

		StringBuilder sb = new StringBuilder();
		Optional<Candidate> name = candidates.findAny();
		sb.append(name);
		System.out.println(sb.toString());
		candidate.setText(sb.toString());
	}

	public void setStates(InternalList list) {
		StringBuilder sb = new StringBuilder();
		//String properties = ("\n"+i+". "+list.get(i).getStateId() + " - "+list.get(i).getName() );
			//String info = String.format("%n\t%-8s|%40s%n", properties);
		String stateName = list.getState().stream().findFirst().get().getName();
		sb.append(stateName);
		System.out.println(sb.toString());
			stateList = sb.toString();

	}
	public void setBills(List<Bill> bills) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bills.size(); i++) {
			String properties = bills.get(i).toString();

			String billInfo = properties;
			sb.append(billInfo);

		}
		System.out.println(sb.toString());

		billData.setText(sb.toString());

	}
	public void setElections(List<Elections> list) {
		StringBuilder sb = new StringBuilder();

		//String electionID = list.stream().filter(el);
		sb.append(list);


		System.out.println(sb.toString());
	}



	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new VoteSmartModule());
		VoteSmartView view = injector.getInstance(VoteSmartView.class);
		view.setVisible(true);
	}

}

