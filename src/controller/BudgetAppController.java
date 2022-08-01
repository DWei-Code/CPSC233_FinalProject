package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.BudgetCategory;

public class BudgetAppController {
	public Stage applicationStage;
	
	 @FXML
	 private VBox rootVbox;
	
    @FXML
    private TextField budgetCategoryName;

    @FXML
    private TextField monthlyCategoryBudget;

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
		// New Table View
		TableView tbv = new TableView();
		// Create two columns 
		TableColumn<String, BudgetCategory> column1 = new TableColumn<>("Budget Category");
		column1.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<Double, BudgetCategory> column2 = new TableColumn<>("Max Budget");
		column2.setCellValueFactory(new PropertyValueFactory<>("maxBudget"));
		// Add two columns into TableView
		tbv.getColumns().add(column1);
		tbv.getColumns().add(column2);
		
		tbv.getItems().add(newCategory);
		
		rootVbox.getChildren().add(tbv);
	}

}
