package models;

public class OneTimeItem extends ExpenseItem{

	public OneTimeItem(String name, double price) {
		super(name, price);
	}

	@Override
	public double getMonthlyExpense() {
		return this.getPrice();
	}

}
