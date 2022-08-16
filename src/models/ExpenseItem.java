package models;

import java.io.Serializable;

public abstract class ExpenseItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double price;
	private String name;
	private int id;
	private static int idGenerator = 1;
	// used for table purposes
	private double monthlyExpense;
	private String itemType;
	
	
	public ExpenseItem(String name, double price) {
		this.name = name;
		this.price = price;
		this.id = idGenerator++;
		this.monthlyExpense = this.calculateMonthlyExpense();
	}
	
	public abstract double calculateMonthlyExpense();

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public double getMonthlyExpense() {
		return monthlyExpense;
	}
	
	public String toString() {
		StringBuilder expenseItemString = new StringBuilder();
		expenseItemString.append('\t');
		expenseItemString.append("Item name: ");
		expenseItemString.append(this.getName());
		expenseItemString.append('\t');
		expenseItemString.append("Item Price: ");
		expenseItemString.append(this.getPrice()+ ",");
		expenseItemString.append('\t');
		expenseItemString.append("Payment occurrence: ");
		expenseItemString.append(this.getItemType()+ ",");
		expenseItemString.append('\t');
		expenseItemString.append("Monthly Payment: ");
		expenseItemString.append(this.getMonthlyExpense());
		return expenseItemString.toString();
	}
	
}
