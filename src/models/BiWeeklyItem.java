package models;

public class BiWeeklyItem extends ExpenseItem{

	public BiWeeklyItem(String name, double price) {
		super(name, price);
	}

	@Override
	public double getMonthlyExpense() {
		return this.getPrice() * 2;
	}

}
