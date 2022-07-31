package models;

import java.util.ArrayList;

public class BudgetCategory {
	private double maxBudget;
	private String name;
	private ArrayList<ExpenseItem> listOfItems = new ArrayList<>();
	
	BudgetCategory(String name, double budget){
		this.name = name;
		this.maxBudget = budget;
	}

	public double getMaxBudget() {
		return maxBudget;
	}

	public String getName() {
		return name;
	}

	public ArrayList<ExpenseItem> getListOfItems() {
		return listOfItems;
	}

	public void setMaxBudget(double maxBudget) {
		this.maxBudget = maxBudget;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
