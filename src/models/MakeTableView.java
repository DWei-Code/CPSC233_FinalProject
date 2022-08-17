package models;

import java.io.FileInputStream;
import java.io.IOException;

import controller.CategoryDetailsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
		TableColumn<BudgetCategory, Void> emptyColumn = new TableColumn<>("");
		/*editButtonColumn.setCellValueFactory(new PropertyValueFactory<>("editButton"));
		TableColumn<BudgetCategory, Button> detailsButtonColumn = new TableColumn<>("Action");
		detailsButtonColumn.setCellValueFactory(new PropertyValueFactory<>("detailsButton"));*/
		

		budgetCategoryTable.getColumns().add(nameColumn);
		budgetCategoryTable.getColumns().add(budgetColumn);
		budgetCategoryTable.getColumns().add(budgetLeftColumn);
		budgetCategoryTable.getColumns().add(overBudgetcolumn);
		budgetCategoryTable.getColumns().add(emptyColumn);
		//budgetCategoryTable.getColumns().add(detailsButtonColumn);

		budgetCategoryTable.setMaxSize(600, 200);
	}

	
	// Deprecated
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

	// Deprecated
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
	// Deprecated
	public void addItemEditButton() {
		TableColumn<ExpenseItem, Void> editButtonColumn = new TableColumn<>("Edit");
		Callback<TableColumn<ExpenseItem, Void>, TableCell<ExpenseItem, Void>> editButtonCell = new Callback<TableColumn<ExpenseItem, Void>, TableCell<ExpenseItem, Void>>() {
			@Override
			public TableCell<ExpenseItem, Void> call(final TableColumn<ExpenseItem, Void> param) {
				final TableCell<ExpenseItem, Void> cell = new TableCell<ExpenseItem, Void>() {
					private Button editButton = new Button("Edit");
					{
						editButton.setOnAction((ActionEvent event) -> {
							ExpenseItem selectedItem = getTableView().getItems().get(getIndex());
                           // System.out.println("selectedData: " + selectedItem.getName());
                            //passItemData(selectedItem);
                            FXMLLoader loader = new FXMLLoader();
                    		try {
								VBox root = loader.load(new FileInputStream("src/application/CategoryDetailView.fxml"));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                    		CategoryDetailsController controller = (CategoryDetailsController) loader.getController();
                    		
                    		Node scene = (Node) event.getSource();
                    		Scene stage = scene.getScene();
                    		controller.categoryDetailStage = (Stage) scene.getScene().getWindow();
                    		controller.categoryDetailStage.setScene(stage);
                    		controller.setLabels(selectedItem);
                    		controller.categoryDetailStage.show();         		
                        });
					}
					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(editButton);
						}
					}
				};
				return cell;
			}
		};
		
		editButtonColumn.setCellFactory(editButtonCell);
		if (!expenseItemTable.getColumns().contains(editButtonColumn)) {
			expenseItemTable.getColumns().add(editButtonColumn);
		}
	}
	
	public void passItemData(ExpenseItem selectedItem) {
		 System.out.println("selectedData: " + selectedItem.getName());
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
	
	public ExpenseItem getExpenseItemData() {
		return expenseItemTable.getSelectionModel().getSelectedItem();
	}

	public BudgetCategory getBudgetCategoryData() {
		return budgetCategoryTable.getSelectionModel().getSelectedItem();
	}
	
}
