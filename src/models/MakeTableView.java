package models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import controller.EditCategoryController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

// reference learning material:
// https://genotechies.medium.com/javafx-tableview-with-dynamically-filled-data-from-a-list-89ff6f8778e1
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
	//TableColumn<BudgetCategory, Void> editButtonColumn = new TableColumn<>("Edit");
	//TableColumn<BudgetCategory, Void> detailsButtonColumn = new TableColumn<>("Details");
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

		budgetCategoryTable.setMaxSize(600, 200);
	}

	

	@SuppressWarnings("exports")
	public void addEditButton(Button buttonToAdd) {
		
		//TableColumn<BudgetCategory, Void> editButtonColumn = new TableColumn<>("Edit");
		Callback<TableColumn<BudgetCategory, Void>, TableCell<BudgetCategory, Void>> editButtonCell = new Callback<TableColumn<BudgetCategory, Void>, TableCell<BudgetCategory, Void>>() {
			@Override
			public TableCell<BudgetCategory, Void> call(final TableColumn<BudgetCategory, Void> param) {
				final TableCell<BudgetCategory, Void> cell = new TableCell<BudgetCategory, Void>() {
					private Button tempButton = new Button("Edit");
					{
//						tempButton.setOnAction((ActionEvent event) -> {
//							BudgetCategory data = getTableView().getItems().get(getIndex());
//							buttonToAdd.getOnAction();
//                            System.out.println("selectedData: " + data.toString());
//                        });
						//tempButton.setOnAction(buttonToAdd.getOnAction());
					}
					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(tempButton);
						}
					}
				};
				return cell;
			}
		};
		//editButtonColumn.setCellFactory(editButtonCell);
//		if (!budgetCategoryTable.getColumns().contains(editButtonColumn)) {
//			budgetCategoryTable.getColumns().add(editButtonColumn);
//		}

	}

	
	@SuppressWarnings("exports")
	public void addDetailButton(Button buttonToAdd) {
		//TableColumn<BudgetCategory, Void> detailsButtonColumn = new TableColumn<>("Details");
		Callback<TableColumn<BudgetCategory, Void>, TableCell<BudgetCategory, Void>> detailsButtonCell = new Callback<TableColumn<BudgetCategory, Void>, TableCell<BudgetCategory, Void>>() {
			@Override
			public TableCell<BudgetCategory, Void> call(final TableColumn<BudgetCategory, Void> param) {
				final TableCell<BudgetCategory, Void> cell = new TableCell<BudgetCategory, Void>() {
					private Button tempButton = new Button("Details");
					{
						tempButton.setOnAction(buttonToAdd.getOnAction());
					}
					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(tempButton);
						}
					}
				};
				return cell;
			}
		};
		//detailsButtonColumn.setCellFactory(detailsButtonCell);
//		if (!budgetCategoryTable.getColumns().contains(detailsButtonColumn)) {
//			budgetCategoryTable.getColumns().add(detailsButtonColumn);
//		}
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

	@SuppressWarnings("exports")
	public Node getBudgetCategoryTable() {
		return budgetCategoryTable;
	}

	@SuppressWarnings("exports")
	public Node getItemCategoryTable() {
		return expenseItemTable;
	}

}
