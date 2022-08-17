package models;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExpenseItemTableView extends MakeTableView{
	
	private TableView<ExpenseItem> expenseItemTable;
	
	@Override
	public void makeTable() {
		expenseItemTable = new TableView<ExpenseItem>();
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
	}

	@Override
	public void updateTable(Object toAdd) {
		expenseItemTable.getItems().add((ExpenseItem) toAdd);
	}

	@Override
	public void clearTable() {
		expenseItemTable.getItems().clear();
	}

	@SuppressWarnings("exports")
	@Override
	public Node getTable() {
		return expenseItemTable;
	}

	@Override
	public Object getData() {
		return expenseItemTable.getSelectionModel().getSelectedItem();
	}

}
