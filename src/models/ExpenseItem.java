package models;

import javafx.scene.control.Button;

public abstract class ExpenseItem {
	private double price;
	private String name;
	private int id;
	public static int idGenerator = 1;
	
	public ExpenseItem(String name, double price) {
		this.name = name;
		this.price = price;
		this.id = idGenerator++;
	}
	
	public abstract double getMonthlyExpense();

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
	
}
