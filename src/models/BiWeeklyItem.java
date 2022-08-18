package models;

/**
 * An ExpenseItem that incurs cost every 2 weeks
 * @author yunwei
 *
 */
@SuppressWarnings("serial")
public class BiWeeklyItem extends ExpenseItem{
	
	/**
	 * sets items name price and type
	 * @param name name of item
	 * @param price price of item per one time payment
	 */
	public BiWeeklyItem(String name, double price) {
		super(name, price);
		this.setItemType("Bi-Weekly");
	}
	
	/**
	 * item cost occurs every 2 weeks so for monthly cost it is price *2
	 */
	@Override
	public double calculateMonthlyExpense() {
		return this.getPrice() * 2;
	}

}
