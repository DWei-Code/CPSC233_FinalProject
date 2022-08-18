package models;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * This class is used to make a TableView containing BudgetCategory objects
 * @author yunwei
 *
 */
public class BudgetCategoryTableView extends MakeTableView {

	private TableView<BudgetCategory> budgetCategoryTable;
	
	/**
	 * Initialize the table with columns to show the BudgetCategory variable data.
	 * Showing BudgetCategory name, max budget, available budget, and how much the user is over budget.
	 * empty column is for adjusting the looks on the screen
	 */
	@Override
	public void makeTable() {
		budgetCategoryTable = new TableView<BudgetCategory>();
		// Initializing the columns of the table
		// The column type must match the variable that it will hold from BudgetCategory
		TableColumn<BudgetCategory, String> nameColumn = new TableColumn<>("Budget Category");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<BudgetCategory, Double> budgetColumn = new TableColumn<>("Max Budget");
		budgetColumn.setCellValueFactory(new PropertyValueFactory<>("maxBudget"));
		TableColumn<BudgetCategory, Double> budgetLeftColumn = new TableColumn<>("Available budget");
		budgetLeftColumn.setCellValueFactory(new PropertyValueFactory<>("budgetLeft"));
		TableColumn<BudgetCategory, Double> overBudgetcolumn = new TableColumn<>("Over budget!");
		overBudgetcolumn.setCellValueFactory(new PropertyValueFactory<>("overBudget"));
		TableColumn<BudgetCategory, Void> emptyColumn = new TableColumn<>("");
		// adding the columns to the ExpenseItem table
		budgetCategoryTable.getColumns().add(nameColumn);
		budgetCategoryTable.getColumns().add(budgetColumn);
		budgetCategoryTable.getColumns().add(budgetLeftColumn);
		budgetCategoryTable.getColumns().add(overBudgetcolumn);
		budgetCategoryTable.getColumns().add(emptyColumn);

		budgetCategoryTable.setMaxSize(600, 200);

	}
	
	/**
	 * update the table with a new object that will be casted to BudgetCategory
	 * all data is then retrieved from the new item and added to the table
	 * @param toAdd BudgetCategory object to extract values from its instance variables to add to the tables columns
	 */
	@Override
	public void updateTable(Object toAdd) {
		budgetCategoryTable.getItems().add((BudgetCategory) toAdd);
	}
	
	/**
	 * clears this table to refresh its contents
	 */
	@Override
	public void clearTable() {
		budgetCategoryTable.getItems().clear();
	}
	
	/**
	 * returns this BudgetCategory table
	 */
	@SuppressWarnings("exports")
	@Override
	public Node getTable() {
		return budgetCategoryTable;
	}
	
	/**
	 * obtains the BudgetCategory object from a selected row within the BudgetCategory table
	 */
	@Override
	public Object getData() {
		return budgetCategoryTable.getSelectionModel().getSelectedItem();
	}
	
	/**
	 * clears selected row in the category table
	 */
	@Override
	public void clearSelection() {
		this.budgetCategoryTable.getSelectionModel().clearSelection();		
	}

}
