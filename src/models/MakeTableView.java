package models;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

// reference learning material:
// https://genotechies.medium.com/javafx-tableview-with-dynamically-filled-data-from-a-list-89ff6f8778e1
public abstract class MakeTableView extends VBox{
	//private TableView<BudgetCategory> budgetCategoryTable = new TableView<BudgetCategory>();
	//private TableView<ExpenseItem> expenseItemTable = new TableView<ExpenseItem>();
	
	//private TableView<?> newTable;
	
//	public MakeTableView(String tableType) {
//		switch (tableType) {
//
//		case "budgetTable":
//			makeBudgetTable();
//			break;
//		case "itemTable":
//			makeItemTable();
//			break;
//		default:
//			break;
//
//		}
//	}
	
	public MakeTableView() {
		makeTable();
		//setTable();
	}
	
	//public abstract void setTable();
	public abstract void makeTable();

	/*private void makeItemTable() {
		TableColumn<ExpenseItem, String> nameColumn = new TableColumn<>("Item Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<ExpenseItem, Double> itemPriceColumn = new TableColumn<>("ItemPrice");
		itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn<ExpenseItem, String> itemTypeColumn = new TableColumn<>("Payment Occurence");
		itemTypeColumn.setCellValueFactory(new PropertyValueFactory<>("itemType"));
		TableColumn<ExpenseItem, Double> monthlyCostColumn = new TableColumn<>("Monthly Cost");
		monthlyCostColumn.setCellValueFactory(new PropertyValueFactory<>("monthlyExpense"));
		TableColumn<ExpenseItem, Void> emptyColumn = new TableColumn<>("");
		expenseItemTable.getColumns().add(nameColumn);
		expenseItemTable.getColumns().add(itemPriceColumn);
		expenseItemTable.getColumns().add(itemTypeColumn);
		expenseItemTable.getColumns().add(monthlyCostColumn);
		expenseItemTable.getColumns().add(emptyColumn);
		
		expenseItemTable.setMaxSize(370, 200);

	}*/
	
	/*private void makeBudgetTable() {
		TableColumn<BudgetCategory, String> nameColumn = new TableColumn<>("Budget Category");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<BudgetCategory, Double> budgetColumn = new TableColumn<>("Max Budget");
		budgetColumn.setCellValueFactory(new PropertyValueFactory<>("maxBudget"));
		TableColumn<BudgetCategory, Double> budgetLeftColumn = new TableColumn<>("Available budget");
		budgetLeftColumn.setCellValueFactory(new PropertyValueFactory<>("budgetLeft"));
		TableColumn<BudgetCategory, Double> overBudgetcolumn = new TableColumn<>("Over budget!");
		overBudgetcolumn.setCellValueFactory(new PropertyValueFactory<>("overBudget"));
		TableColumn<BudgetCategory, Void> emptyColumn = new TableColumn<>("");
		

		budgetCategoryTable.getColumns().add(nameColumn);
		budgetCategoryTable.getColumns().add(budgetColumn);
		budgetCategoryTable.getColumns().add(budgetLeftColumn);
		budgetCategoryTable.getColumns().add(overBudgetcolumn);
		budgetCategoryTable.getColumns().add(emptyColumn);
		//budgetCategoryTable.getColumns().add(detailsButtonColumn);

		budgetCategoryTable.setMaxSize(600, 200);
	}*/
	
	public abstract void updateTable(Object toAdd);

	public abstract void clearTable();
	
	@SuppressWarnings("exports")
	public abstract Node getTable();
	
	public abstract Object getData();

	/*public void updateItemTable(ExpenseItem itemToAdd) {
		expenseItemTable.getItems().add(itemToAdd);
	}

	public void clearItemTable() {
		expenseItemTable.getItems().clear();
	}

	public void updateBudgetTable(BudgetCategory categoryToAdd) {
		budgetCategoryTable.getItems().add(categoryToAdd);
	}

	public void clearBudgetTable() {
		budgetCategoryTable.getItems().clear();
	}

	@SuppressWarnings("exports")
	public Node getBudgetCategoryTable() {
		return budgetCategoryTable;
	}

	@SuppressWarnings("exports")
	public Node getItemCategoryTable() {
		return expenseItemTable;
	}
	
	public ExpenseItem getExpenseItemData() {
		return expenseItemTable.getSelectionModel().getSelectedItem();
	}

	public BudgetCategory getBudgetCategoryData() {
		return budgetCategoryTable.getSelectionModel().getSelectedItem();
	}*/
	
}
