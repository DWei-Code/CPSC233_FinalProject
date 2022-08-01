package models;

import java.util.ArrayList;

import javafx.scene.control.Button;

public class BudgetCategory {
	private double maxBudget;
	private String name;
	private ArrayList<ExpenseItem> listOfItems = new ArrayList<>();
	private Button editButton;
	
	public BudgetCategory(String name, double budget){
		this.name = name;
		this.maxBudget = budget;
		this.setEditButton(new Button("Edit Category"));
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

	public Button getEditButton() {
		return editButton;
	}

	public void setEditButton(Button editButton) {
		this.editButton = editButton;
	}
	
}
