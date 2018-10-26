package kohn.rx.votesmart;

import java.util.List;


public class Categories {

	private List<Category> category;

	public List<Category> getCategory() {
		return category;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n").append(category);
		return sb.toString();
	}
}
