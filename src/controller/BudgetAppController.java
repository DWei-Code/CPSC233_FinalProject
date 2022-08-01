package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.BudgetCategory;

public class BudgetAppController {
	public Stage applicationStage;
	// table view for categories
	TableView budgetCategoryTable = new TableView();

    @FXML
    private VBox rootVbox;

	@FXML
	private TextField budgetCategoryName;

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
		updateTable(newCategory);
		
	}

	private void updateTable(BudgetCategory newCategory) {
		// add new table if it does not exist otherwise add items to table
		if (!rootVbox.getChildren().contains(budgetCategoryTable)) {
			TableColumn<String, BudgetCategory> column1 = new TableColumn<>("Budget Category");
			column1.setCellValueFactory(new PropertyValueFactory<>("name"));
			TableColumn<Double, BudgetCategory> column2 = new TableColumn<>("Max Budget");
			column2.setCellValueFactory(new PropertyValueFactory<>("maxBudget"));
			TableColumn<Button, BudgetCategory> column3 = new TableColumn<>("Action");
			column3.setCellValueFactory(new PropertyValueFactory<>("editButton"));
			budgetCategoryTable.getColumns().add(column1);
			budgetCategoryTable.getColumns().add(column2);
			budgetCategoryTable.getColumns().add(column3);
			budgetCategoryTable.setMaxSize(400, 100);
			//budgetCategoryTable.applyCss();

			rootVbox.getChildren().add(3, budgetCategoryTable);
		}
		budgetCategoryTable.getItems().add(newCategory);

	}

}
