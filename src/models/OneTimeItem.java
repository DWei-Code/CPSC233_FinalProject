package models;
/**
 * An ExpenseItem that incurs cost once
 * @author yunwei
 *
 */
@SuppressWarnings("serial")
public class OneTimeItem extends ExpenseItem{
	
	/**
	 * sets items name price and type
	 * @param name name of item
	 * @param price price of item per one time payment
	 */
	public OneTimeItem(String name, double price) {
		super(name, price);
		this.setItemType("One Time");
	}
	
	/**
	 * cost only occurs once, so return the price of this item
	 */
	@Override
	public double calculateMonthlyExpense() {
		return this.getPrice();
	}

}
