package kohn.rx.votesmart;

import kohn.rx.votesmart.InternalList.State.GeneralInfo;

import java.util.ArrayList;

public class Bills {
	
	private ArrayList<Bill> bill;
    private GeneralInfo generalInfo;
    	
	public Bills(ArrayList<Bill> bill) {
		this.bill = bill;
	}
	
	public ArrayList<Bill> getBill() {
		return bill;
	}	

	public GeneralInfo getGeneralInfo() {
		return generalInfo;
	}

	@Override
	public String toString() {
		return "\n" + this.bill;

	}


}
