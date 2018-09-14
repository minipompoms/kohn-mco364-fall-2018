package kohn.rx.votesmart;


import kohn.rx.votesmart.InternalList.State.GeneralInfo;

import java.util.List;

public class Elections {
	private List<Election> election;
	//private Election election;
	private GeneralInfo generalInfo;
	private String zipMessage;

	public Elections(List<Election> election) {

		this.election = election;
	}

	public List getElection() {

		return election;
	}

	public String getZipMessage() {
		return zipMessage;
	}

	public GeneralInfo getGeneralInfo() {
		return generalInfo;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n").append(election);
		return sb.toString();
	}

	public class Election {
		private String electionYear;
		private String stateId;
		private String name;
		private String electionId;
		private String special;
		private Stage stage;

		public Election(String electionYear, String stateId, String name, String electionId, String special,
					Stage stage) {
			this.electionYear = electionYear;
			this.stateId = stateId;
			this.name = name;
			this.electionId = electionId;
			this.special = special;
			this.stage = stage;
		}

		public String getElectionYear() {
			return electionYear;
		}

		public String getStateId() {
			return stateId;
		}

		public String getName() {
			return name;
		}

		public String getElectionId() {
			return electionId;
		}

		public String getSpecial() {
			return special;
		}

		public Stage getStage() {
			return stage;
		}

		@Override
		public String toString() {
			return 
			"\n" + name +" " +electionId;
		}
	}
}