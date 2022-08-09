package models;

public class WeeklyItem extends ExpenseItem{

	public WeeklyItem(String name, double price) {
		super(name, price);
	}

	@Override
	public double getMonthlyExpense() {
		return this.getPrice() * 4;
	}

}
