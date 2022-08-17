package models;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BudgetCategoryTableView extends MakeTableView {

	private TableView<BudgetCategory> budgetCategoryTable;

	@Override
	public void makeTable() {
		budgetCategoryTable = new TableView<BudgetCategory>();
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

		budgetCategoryTable.setMaxSize(600, 200);

	}

	@Override
	public void updateTable(Object toAdd) {
		budgetCategoryTable.getItems().add((BudgetCategory) toAdd);
	}

	@Override
	public void clearTable() {
		budgetCategoryTable.getItems().clear();
	}

	@SuppressWarnings("exports")
	@Override
	public Node getTable() {
		return budgetCategoryTable;
	}

	@Override
	public Object getData() {
		return budgetCategoryTable.getSelectionModel().getSelectedItem();
	}

}
