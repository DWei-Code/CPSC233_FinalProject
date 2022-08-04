package models;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class MakeTableView extends VBox{
	private TableView<BudgetCategory> budgetCategoryTable = new TableView<BudgetCategory>();
	private TableView<ExpenseItem> expenseItemTable = new TableView<ExpenseItem>();

	public MakeTableView(String tableType) {
		switch (tableType) {
		
		case "budgetTable":
			makeBudgetTable();
			break;
		case "itemTable":
			makeItemTable();
			break;
		default:
			break;

		}
	}

	private void makeItemTable() {
		TableColumn<ExpenseItem, String> nameColumn = new TableColumn<>("Item Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<ExpenseItem, Double> itemPriceColumn = new TableColumn<>("ItemPrice");
		itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		expenseItemTable.getColumns().add(nameColumn);
		expenseItemTable.getColumns().add(itemPriceColumn);

		expenseItemTable.setMaxSize(500, 200);
		
	}

	private void makeBudgetTable() {
		TableColumn<BudgetCategory, String> nameColumn = new TableColumn<>("Budget Category");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<BudgetCategory, Double> budgetColumn = new TableColumn<>("Max Budget");
		budgetColumn.setCellValueFactory(new PropertyValueFactory<>("maxBudget"));
		TableColumn<BudgetCategory, Double> budgetLeftColumn = new TableColumn<>("Available budget");
		budgetLeftColumn.setCellValueFactory(new PropertyValueFactory<>("budgetLeft"));
		TableColumn<BudgetCategory, Double> overBudgetcolumn = new TableColumn<>("Over budget!");
		overBudgetcolumn.setCellValueFactory(new PropertyValueFactory<>("overBudget"));
		TableColumn<BudgetCategory, Button> editButtonColumn = new TableColumn<>("Action");
		editButtonColumn.setCellValueFactory(new PropertyValueFactory<>("editButton"));
		TableColumn<BudgetCategory, Button> detailsButtonColumn = new TableColumn<>("Action");
		detailsButtonColumn.setCellValueFactory(new PropertyValueFactory<>("detailsButton"));
		
		budgetCategoryTable.getColumns().add(nameColumn);
		budgetCategoryTable.getColumns().add(budgetColumn);
		budgetCategoryTable.getColumns().add(budgetLeftColumn);
		budgetCategoryTable.getColumns().add(overBudgetcolumn);
		budgetCategoryTable.getColumns().add(editButtonColumn);
		budgetCategoryTable.getColumns().add(detailsButtonColumn);
		
		budgetCategoryTable.setMaxSize(560, 200);
	}
	
	public void updateItemTable(ExpenseItem itemToAdd) {
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

	public Node getBudgetCategoryTable() {
		return budgetCategoryTable;
	}
	
	public Node getItemCategoryTable() {
		return expenseItemTable;
	}
	
}
