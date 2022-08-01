package models;

import java.util.ArrayList;

import javafx.scene.control.Button;

public class BudgetCategory {
	private double maxBudget;
	private double budgetLeft;
	private String name;
	private ArrayList<ExpenseItem> listOfItems = new ArrayList<>();
	private Button editButton;
	
	public BudgetCategory(String name, double budget){
		this.name = name;
		this.maxBudget = budget;
		this.budgetLeft = budget;
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
	
	public void updateBudget() {
		for(ExpenseItem ei : listOfItems) {
			if(budgetLeft - ei.getPrice() < 0) {
				System.out.println("No more budget left");
			}else {
				budgetLeft -= ei.getPrice();
				System.out.println(budgetLeft);
			}
		}
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
