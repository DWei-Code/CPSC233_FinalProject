package models;

import java.util.ArrayList;

import javafx.scene.control.Button;

public class BudgetCategory {
	private double maxBudget;
	private double budgetLeft;
	private double overBudget;
	private String name;
	private ArrayList<ExpenseItem> listOfItems = new ArrayList<>();
	private Button editButton;

	public BudgetCategory(String name, double budget) {
		this.name = name;
		this.maxBudget = budget;
		this.budgetLeft = budget;
		this.overBudget = 0.0;
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

	public double getBudgetLeft() {
		return budgetLeft;
	}

	public double getOverBudget() {
		return overBudget;
	}

	public void setMaxBudget(double maxBudget) {
		this.maxBudget = maxBudget;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String updateBudget(ExpenseItem newItem) {
		String userMessage = "";
		if (budgetLeft - newItem.getPrice() < 0) {
			userMessage = String.format("Gone over buget by: %.02f.", Math.abs(budgetLeft - newItem.getPrice()));
			if (overBudget == 0.0) {
				overBudget = Math.abs(budgetLeft - newItem.getPrice());
				budgetLeft = 0;
			} else {
				overBudget =  Math.abs(overBudget - newItem.getPrice());
			}
		} else {
			budgetLeft -= newItem.getPrice();
			userMessage = String.format("Budget left for category %s:  %.02f.", this.name, budgetLeft);
		}
		return userMessage;
	}

	@SuppressWarnings("exports")
	public Button getEditButton() {
		return editButton;
	}

	@SuppressWarnings("exports")
	public void setEditButton(Button editButton) {
		this.editButton = editButton;
	}

}
