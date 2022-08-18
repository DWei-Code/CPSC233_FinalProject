package models;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * This class is used to make a TableView containing ExpenseItem objects
 * @author yunwei
 *
 */
public class ExpenseItemTableView extends MakeTableView{
	
	private TableView<ExpenseItem> expenseItemTable;
	
	/**
	 * Initialize the table with columns to show the ExpenseItems variable data.
	 * Showing ExpenseItem name, price, type, and monthly cost
	 * empty column is for adjusting the looks on the screen
	 */
	@Override
	public void makeTable() {
		expenseItemTable = new TableView<ExpenseItem>();
		// Initializing the columns of the table
		// The column type must match the variable that it will hold from ExpenseItem
		TableColumn<ExpenseItem, String> nameColumn = new TableColumn<>("Item Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<ExpenseItem, Double> itemPriceColumn = new TableColumn<>("ItemPrice");
		itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn<ExpenseItem, String> itemTypeColumn = new TableColumn<>("Payment Occurence");
		itemTypeColumn.setCellValueFactory(new PropertyValueFactory<>("itemType"));
		TableColumn<ExpenseItem, Double> monthlyCostColumn = new TableColumn<>("Monthly Cost");
		monthlyCostColumn.setCellValueFactory(new PropertyValueFactory<>("monthlyExpense"));
		TableColumn<ExpenseItem, Void> emptyColumn = new TableColumn<>("");
		// adding the columns to the ExpenseItem table
		expenseItemTable.getColumns().add(nameColumn);
		expenseItemTable.getColumns().add(itemPriceColumn);
		expenseItemTable.getColumns().add(itemTypeColumn);
		expenseItemTable.getColumns().add(monthlyCostColumn);
		expenseItemTable.getColumns().add(emptyColumn);
		// setting table size
		expenseItemTable.setMaxSize(370, 200);
	}
	
	/**
	 * update the table with a new object that will be casted to ExpenseItem
	 * all data is then retrieved from the new item and added to the table
	 */
	@Override
	public void updateTable(Object toAdd) {
		expenseItemTable.getItems().add((ExpenseItem) toAdd);
	}
	
	/**
	 * clears this table to refresh its contents
	 */
	@Override
	public void clearTable() {
		expenseItemTable.getItems().clear();
	}
	
	/**
	 * returns this ExpenseItem table
	 */
	@SuppressWarnings("exports")
	@Override
	public Node getTable() {
		return expenseItemTable;
	}
	
	/**
	 * obtains the ExpenseItem object from a selected row within the ExpenseItem table
	 */
	@Override
	public Object getData() {
		return expenseItemTable.getSelectionModel().getSelectedItem();
	}
	
	/**
	 * clears selected row in items table
	 */
	public void clearSelection() {
		this.expenseItemTable.getSelectionModel().clearSelection();
	}

}
