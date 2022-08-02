package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.BudgetCategory;
import models.ExpenseItem;

public class BudgetAppController {
	public Stage applicationStage;
	// table view for categories
	TableView budgetCategoryTable = new TableView();
	ArrayList<String> categoryNames = new ArrayList<String>();
	ArrayList<BudgetCategory> categories = new ArrayList<BudgetCategory>();
	
	@FXML
    private Label userMessage;
	
	@FXML
	private VBox rootVbox;

	@FXML
	private TextField budgetCategoryName;

	@FXML
	private ChoiceBox<String> categoryChoiceBox;

	@FXML
	private TextField monthlyCategoryBudget;

	@FXML
	private TextField expenseItemCost;

	@FXML
	private TextField expenseItemName;

	@FXML
	void addCategory(ActionEvent event) {
		String categoryName = budgetCategoryName.getText();
		String categoryBudget = monthlyCategoryBudget.getText();
		double categoryBudgetNumber = Double.parseDouble(categoryBudget);
		System.out.println("Category Added: " + categoryName + " Budget:" + categoryBudget);

		BudgetCategory newCategory = new BudgetCategory(categoryName, categoryBudgetNumber);
		categoryNames.add(newCategory.getName());
		categories.add(newCategory);
		updateTable(newCategory);
		updateChoiceBox();
	}

	private void updateChoiceBox() {
		categoryChoiceBox.setItems(FXCollections.observableArrayList(categoryNames));
	}

	private void updateTable(BudgetCategory newCategory) {
		// add new table if it does not exist otherwise add items to table
		if (!rootVbox.getChildren().contains(budgetCategoryTable)) {
			TableColumn<String, BudgetCategory> nameColumn = new TableColumn<>("Budget Category");
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			TableColumn<Double, BudgetCategory> budgetColumn = new TableColumn<>("Max Budget");
			budgetColumn.setCellValueFactory(new PropertyValueFactory<>("maxBudget"));
			TableColumn<Double, BudgetCategory> budgetLeftColumn = new TableColumn<>("Available budget");
			budgetLeftColumn.setCellValueFactory(new PropertyValueFactory<>("budgetLeft"));
			TableColumn<Double, BudgetCategory> overBudgetcolumn = new TableColumn<>("Over budget!");
			overBudgetcolumn.setCellValueFactory(new PropertyValueFactory<>("overBudget"));
			TableColumn<Button, BudgetCategory> editButtonColumn = new TableColumn<>("Action");
			editButtonColumn.setCellValueFactory(new PropertyValueFactory<>("editButton"));
			budgetCategoryTable.getColumns().add(nameColumn);
			budgetCategoryTable.getColumns().add(budgetColumn);
			budgetCategoryTable.getColumns().add(budgetLeftColumn);
			budgetCategoryTable.getColumns().add(overBudgetcolumn);
			budgetCategoryTable.getColumns().add(editButtonColumn);
			budgetCategoryTable.setMaxSize(500, 200);
			// budgetCategoryTable.applyCss();

			rootVbox.getChildren().add(3, budgetCategoryTable);
		}
		budgetCategoryTable.getItems().add(newCategory);
	}

	@FXML
	void addItem(ActionEvent event) {
		String itemName = expenseItemName.getText();
		String expenseItemPrice = expenseItemCost.getText();
		double itemPrice = Double.parseDouble(expenseItemPrice);
		String choiceBoxSelected = categoryChoiceBox.getValue();
		System.out.println("Item Added: " + itemName + " Price:" + itemPrice + " Choice Box: " + choiceBoxSelected);

		ExpenseItem newItem = new ExpenseItem(itemName, itemPrice);
		
		budgetCategoryTable.getItems().clear();
		for(BudgetCategory bc : categories) {
			if(bc.getName().equals(choiceBoxSelected)) {
				//bc.getListOfItems().add(newCategory);
				//System.out.println(bc.getListOfItems().get(0).getName());
				userMessage.setText(bc.updateBudget(newItem));
			}
			budgetCategoryTable.getItems().add(bc);
		}
		
		
		
	}

}
