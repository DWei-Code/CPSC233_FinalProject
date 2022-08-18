package models;
/**
 * An ExpenseItem that incurs cost every week
 * @author yunwei
 *
 */
@SuppressWarnings("serial")
public class WeeklyItem extends ExpenseItem{
	
	/**
	 * sets items name price and type
	 * @param name name of item
	 * @param price price of item per one time payment
	 */
	public WeeklyItem(String name, double price) {
		super(name, price);
		this.setItemType("Weekly");
	}
	
	/**
	 * item cost occurs every week so for monthly cost it is price * 4
	 */
	@Override
	public double calculateMonthlyExpense() {
		return this.getPrice() * 4;
	}

}
