package models;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
/**
 * Abstract class to initialize a table view of either type BudgetCategory or ExpenseItem
 * Extends Vbox since the TableView uses it to make the table
 * @author yunwei
 * reference learning material:
 * https://genotechies.medium.com/javafx-tableview-with-dynamically-filled-data-from-a-list-89ff6f8778e1
 */
public abstract class MakeTableView extends VBox{

	/**
	 * no args constructor used to call makeTable to initialize and add columns to the table
	 */
	public MakeTableView() {
		makeTable();
		//setTable();
	}
	
	/**
	 * used to initialize the columns of the table
	 */
	public abstract void makeTable();
	
	/**
	 * updates the table with a new object, object must match the class the table is looking for
	 * @param toAdd object to extract values from its instance variables to add to the tables columns
	 */
	public abstract void updateTable(Object toAdd);
	
	/**
	 * clears the table of its data when a table calls this
	 */
	public abstract void clearTable();
	
	/**
	 * 
	 * @return the table that calls this
	 */
	@SuppressWarnings("exports")
	public abstract Node getTable();
	
	/**
	 * 
	 * @return the object from the row that was selected within the table
	 */
	public abstract Object getData();
	
	/**
	 * clears selected row
	 */
	public abstract void clearSelection();
	
}
