package models;

import java.io.Serializable;

/**
 * items the user is going to spend money on and budget for
 * @author yunwei
 *
 */
public abstract class ExpenseItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private double price;
	private String name;
	private int id;
	private static int idGenerator = 1;
	// used for table purposes
	private double monthlyExpense;
	private String itemType;
	
	/**
	 * constructor to initialize the ExpenseItem
	 * @param name name of item
	 * @param price price of item per one payment
	 */
	public ExpenseItem(String name, double price) {
		this.name = name;
		this.price = price;
		this.id = idGenerator++;
		this.monthlyExpense = this.calculateMonthlyExpense();
	}
	
	/**
	 * calculates the items monthly cost based on what item type it is
	 * @return the monthly cost of this item
	 */
	public abstract double calculateMonthlyExpense();
	
	/**
	 * 
	 * @return the price of this item
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * 
	 * @return name of item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets new price for item
	 * @param price new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * sets name for item
	 * @param name new item
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return the id of this item
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return item type as a string
	 */
	public String getItemType() {
		return itemType;
	}
	
	/**
	 * sets item type
	 * @param itemType item type
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	/**
	 * 
	 * @return this items monthly cost
	 */
	public double getMonthlyExpense() {
		return monthlyExpense;
	}
	
	/**
	 * To string to print a formatted legible layout of this items information
	 */
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
