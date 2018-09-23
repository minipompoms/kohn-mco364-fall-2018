package kohn.rx.votesmart;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;

import java.util.List;

public class VoteSmartFeed extends AbstractModule {


	private CandidateList candidateList;
	private StateList stateList;
	private Bills bills;
	private Elections elections;
	private List<Candidate> candidates;
	private Categories categories;
	private List<Bill> billList;

	
	@Inject
	public VoteSmartFeed(CandidateList candidateList, StateList stateList,
						 Bills bills, Elections elections, List<Candidate> candidates, Categories categories, List<Bill> billList ) {
		this.candidateList = candidateList;	
		this.stateList = stateList;
		this.bills = bills;
		this.elections = elections;
		this.candidates = candidates;
		this.categories = categories;
		this.billList = billList;
	}


	public CandidateList getCandidateList() {
		return candidateList;
	}
	
	public StateList getStateList() {
		return stateList;
	}
	
	public Bills getBills() {
		return bills;
	}

	public List<Bill> getBillsList(){
		return billList;
	}

	public Elections getElections() {
		return elections;
	}

	public List<Candidate> getCandidates(){ return candidates;}

	public Categories getCategories(){return categories; }



}
