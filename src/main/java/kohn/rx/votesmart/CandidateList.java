package kohn.rx.votesmart;

import kohn.rx.votesmart.InternalList.State.GeneralInfo;

import java.util.List;

public class CandidateList {

	private GeneralInfo generalInfo;
	private List<Candidate> candidate;

	public CandidateList(GeneralInfo generalInfo, List<Candidate> candidate) {
		this.generalInfo = generalInfo;
		this.candidate = candidate;
	}

	public GeneralInfo getGeneralInfo() {
		return generalInfo;
	}

	public List<Candidate> getCandidate() {
		return candidate;
	}


}
