package models;

public class OneTimeItem extends ExpenseItem{

	public OneTimeItem(String name, double price) {
		super(name, price);
		this.setItemType("One Time");
	}

	@Override
	public double calculateMonthlyExpense() {
		return this.getPrice();
	}

}
