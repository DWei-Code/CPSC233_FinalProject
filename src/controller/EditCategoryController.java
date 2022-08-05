package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.BudgetCategory;

public class EditCategoryController {

	@SuppressWarnings("exports")
	public Stage editCategoryStage;
	@SuppressWarnings("exports")
	public Scene mainScene;
	public BudgetAppController refreshCategoryData;

	private BudgetCategory editCategory;
	private BudgetCategory beforeEditCategory;
	
	
    @FXML
    private TextField selectedCategoryName;

    @FXML
    private TextField selectedCategoryBudget;
    
    @FXML
    private Label editUserMessage;
    
    @FXML
	void switchToMainScene(ActionEvent event) {
		editCategoryStage.setScene(mainScene);
	}
    
    @FXML
    void passDataToMainScene(ActionEvent event) {
    	refreshCategoryData.updateCategory(beforeEditCategory, editCategory);
    	editCategoryStage.setUserData(event); 	
    	editCategoryStage.setScene(mainScene);
    }
    
    @FXML
    void updateCategory(ActionEvent event) {
    	editCategory.setName(selectedCategoryName.getText());
    	editCategory.setMaxBudget(Double.parseDouble(selectedCategoryBudget.getText()));
    	editUserMessage.setText("Category updated!");
    }
    
	public BudgetCategory getEditCategory() {
		return editCategory;
	}

	public void setEditCategory(BudgetCategory editCategory) {
		this.editCategory = editCategory;
	}

	public void setViewFields(BudgetCategory categoryToEdit) {
		selectedCategoryName.setText(categoryToEdit.getName());
		selectedCategoryBudget.setText(Double.toString(categoryToEdit.getMaxBudget()));
	}

	public void setBeforeEditCategory(BudgetCategory beforeEditCategory) {
		this.beforeEditCategory = beforeEditCategory;
	}
	
	
}
