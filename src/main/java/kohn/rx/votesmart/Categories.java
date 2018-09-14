package kohn.rx.votesmart;

import java.util.ArrayList;


public class Categories {
	private ArrayList<Category> category;

	public ArrayList<Category> getCategory() {
		return category;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n").append(category);
		return sb.toString();
	}
}
