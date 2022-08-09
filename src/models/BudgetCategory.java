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
	private Button detailsButton;
	
	public BudgetCategory(String name, double budget) {
		this.name = name;
		this.maxBudget = budget;
		this.budgetLeft = budget;
		this.overBudget = 0.0;
		//this.setEditButton(new Button("Edit Category"));
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
		updateOtherBudgets(this.maxBudget, maxBudget);
		this.maxBudget = maxBudget;
	}
	
	/**
	 * method used to update budget instance variables when user edits max budget
	 * @param currentMaxBudget max budget before editing
	 * @param newMaxBudget new set max budget
	 */
	public void updateOtherBudgets(double currentMaxBudget, double newMaxBudget) {
		/*if there is budget left update budget left only
		 *else check if budget increase is higher than current over budget,
		 *if it is update budget left and set budget over back to zero
		 *if its not update over budget only keep budget left at zero.
		 */
		if(this.budgetLeft != 0) {
			this.budgetLeft += (newMaxBudget - currentMaxBudget);
		}else {
			if((newMaxBudget - currentMaxBudget) > this.overBudget) {
				this.budgetLeft = (newMaxBudget - currentMaxBudget) - this.overBudget;
				this.overBudget = 0;
			}else {
				this.overBudget = Math.abs((newMaxBudget - currentMaxBudget) - this.overBudget);
			}
		}
	}
	

	public void setName(String name) {
		this.name = name;
	}

	public String updateBudget(ExpenseItem newItem) {
		String userMessage = "";
		if (budgetLeft - newItem.getMonthlyExpense() < 0) {
			userMessage = String.format("Gone over buget by: %.02f.", Math.abs(budgetLeft - newItem.getMonthlyExpense()));
			if (overBudget == 0.0) {
				overBudget = Math.abs(budgetLeft - newItem.getMonthlyExpense());
				budgetLeft = 0;
			} else {
				overBudget =  overBudget + newItem.getMonthlyExpense();
				userMessage = String.format("Gone over buget by: %.02f.", overBudget);
			}
		} else {
			budgetLeft -= newItem.getMonthlyExpense();
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
	
	@SuppressWarnings("exports")
	public Button getDetailsButton() {
		return detailsButton;
	}

	@SuppressWarnings("exports")
	public void setDetailsButton(Button detailsButton) {
		this.detailsButton = detailsButton;
	}
}
