package models;

public abstract class ExpenseItem {
	private double price;
	private String name;
	
	public ExpenseItem(String name, double price) {
		this.name = name;
		this.price = price;
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
	
}
