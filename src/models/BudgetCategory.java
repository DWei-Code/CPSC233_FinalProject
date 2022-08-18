package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * class is used to track all budget categories the user enters
 * @author yunwei
 *
 */
public class BudgetCategory implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private double maxBudget;
	private double budgetLeft;
	private double overBudget;
	private String name;
	private ArrayList<ExpenseItem> listOfItems = new ArrayList<>();
	
	/**
	 * initializes the BudgetCategory object
	 * @param name name of category
	 * @param budget monthly set budget for this category
	 */
	public BudgetCategory(String name, double budget) {
		this.name = name;
		this.maxBudget = budget;
		this.budgetLeft = budget;
		this.overBudget = 0.0;
	}
	
	/**
	 * copy constructor to same budget category name to use for unique category checks
	 * @param toCopy budget category to copy
	 */
	public BudgetCategory(BudgetCategory toCopy) {
		this.name = toCopy.name;
	}
	
	/**
	 * 
	 * @return categories monthly set budget
	 */
	public double getMaxBudget() {
		return maxBudget;
	}
	
	/**
	 * 
	 * @return categories name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return All items that belong to this category as an ArrayList
	 */
	public ArrayList<ExpenseItem> getListOfItems() {
		return listOfItems;
	}
	
	/**
	 * 
	 * @return categories available budget
	 */
	public double getBudgetLeft() {
		return budgetLeft;
	}
	
	/**
	 * 
	 * @return how over budget the user is for this category
	 */
	public double getOverBudget() {
		return overBudget;
	}
	
	/**
	 * sets the monthly budget of the category
	 * @param maxBudget the new budget
	 */
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
	
	/**
	 * sets the category name
	 * @param name name of category
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * updates the budget left and over budget variables based on item added
	 * @param newItem item to add under this category
	 * @return user message telling the user the budget update
	 */
	public String updateBudget(ExpenseItem newItem) {
		String userMessage = "";
		// if there is no more budget left, transfer the rest of the spending into overBudget
		// else decrease the amount of budget left
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
	
	/**
	 * adds an items budget back into this category's budget
	 * @param beforeEdit the item budget to add back in.
	 */
	public void addBudget(ExpenseItem beforeEdit) {
		if(budgetLeft != 0) {
			budgetLeft += beforeEdit.getMonthlyExpense();
		}else if (overBudget - beforeEdit.getMonthlyExpense() < 0 ){
			budgetLeft += Math.abs(overBudget - beforeEdit.getMonthlyExpense());
			overBudget = 0.0;
		}else {
			overBudget -= beforeEdit.getMonthlyExpense();
		}
	}
	
	/**
	 * To string to print a formatted legible layout of this categories information
	 */
	public String toString() {
		double totalSpending = 0.0;
		StringBuilder budgetCategoryString = new StringBuilder();
		budgetCategoryString.append("=======================");
		budgetCategoryString.append('\n');
		budgetCategoryString.append("Budget category name: ");
		budgetCategoryString.append(this.getName());
		budgetCategoryString.append('\n');
		budgetCategoryString.append("=======================");
		budgetCategoryString.append('\n');
		for(ExpenseItem categoriesItem : listOfItems) {
			totalSpending += categoriesItem.getMonthlyExpense();
			budgetCategoryString.append(categoriesItem.toString());
			budgetCategoryString.append('\n');
		}
		budgetCategoryString.append('\t');
		budgetCategoryString.append("Total spending: ");
		budgetCategoryString.append(totalSpending);
		budgetCategoryString.append('\n');
		budgetCategoryString.append('\t');
		budgetCategoryString.append("Max monthly budget: ");
		budgetCategoryString.append(this.getMaxBudget()+ ",");
		budgetCategoryString.append('\t');
		budgetCategoryString.append("Budget left: ");
		budgetCategoryString.append(this.getBudgetLeft()+ ",");
		budgetCategoryString.append('\t');
		budgetCategoryString.append("Over budget: ");
		budgetCategoryString.append(this.getOverBudget());
		return budgetCategoryString.toString();
	}

	
}
