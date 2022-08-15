package models;

public abstract class ExpenseItem {
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
	
}
