package models;

public class WeeklyItem extends ExpenseItem{

	public WeeklyItem(String name, double price) {
		super(name, price);
		this.setItemType("Weekly");
	}

	@Override
	public double calculateMonthlyExpense() {
		return this.getPrice() * 4;
	}

}
