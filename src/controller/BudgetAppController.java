package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import application.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.BudgetCategory;
import models.ExpenseItem;
import models.MakeTableView;

public class BudgetAppController {
	public Stage applicationStage;
	// table view for categories
	private MakeTableView budgetCategoryTable = new MakeTableView("budgetTable");
	private MakeTableView itemsTable = new MakeTableView("itemTable");
	private ArrayList<String> categoryNames = new ArrayList<String>();
	private ArrayList<BudgetCategory> categories = new ArrayList<BudgetCategory>();
	
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
		Button setDetailAction = new Button("Details");
		setDetailAction.setOnAction(detailsEvent -> showDetailView(newCategory));
		newCategory.setDetailsButton(setDetailAction);
		
		updateTable(newCategory);
		updateChoiceBox();
	}

	private void showDetailView(BudgetCategory categoryToShow) {
		Scene mainScene = applicationStage.getScene();
		Main.showCategoryDetails(applicationStage, mainScene, categoryToShow);
	}

	private void updateChoiceBox() {
		categoryChoiceBox.setItems(FXCollections.observableArrayList(categoryNames));
	}

	private void updateTable(BudgetCategory newCategory) {
		// add new table if it does not exist otherwise add items to table
		if (!rootVbox.getChildren().contains(budgetCategoryTable.getBudgetCategoryTable())) {		
			//budgetCategoryTable.addTableToStage(rootVbox);
			rootVbox.getChildren().add(3, budgetCategoryTable.getBudgetCategoryTable());
		}
		budgetCategoryTable.updateBudgetTable(newCategory);
	}

	@FXML
	void addItem(ActionEvent event) {
		String itemName = expenseItemName.getText();
		String expenseItemPrice = expenseItemCost.getText();
		double itemPrice = Double.parseDouble(expenseItemPrice);
		String choiceBoxSelected = categoryChoiceBox.getValue();
		System.out.println("Item Added: " + itemName + " Price:" + itemPrice + " Choice Box: " + choiceBoxSelected);

		ExpenseItem newItem = new ExpenseItem(itemName, itemPrice);
		//rootVbox.getChildren().add(itemsTable.getItemCategoryTable());
		//itemsTable.clearItemTable();
		//itemsTable.updateItemTable(newItem);

		budgetCategoryTable.clearBudgetTable();
		for(BudgetCategory bc : categories) {
			if(bc.getName().equals(choiceBoxSelected)) {
				bc.getListOfItems().add(newItem);
				userMessage.setText(bc.updateBudget(newItem));
			}
			budgetCategoryTable.updateBudgetTable(bc);
		}
		
	}

}
