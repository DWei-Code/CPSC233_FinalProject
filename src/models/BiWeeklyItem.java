package models;

public class BiWeeklyItem extends ExpenseItem{

	public BiWeeklyItem(String name, double price) {
		super(name, price);
		this.setItemType("Bi-Weekly");
	}

	@Override
	public double calculateMonthlyExpense() {
		return this.getPrice() * 2;
	}

}
