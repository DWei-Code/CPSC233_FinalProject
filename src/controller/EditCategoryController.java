package controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.BudgetCategory;
import models.Utility;
/**
 * controller class for controlling UI for EditCategoryView.fxml
 * 
 * @author yunwei
 *
 */
public class EditCategoryController {

	@SuppressWarnings("exports")
	public Stage editCategoryStage;
	@SuppressWarnings("exports")
	public Scene mainScene;
	public BudgetAppController refreshCategoryData;
	public ArrayList<BudgetCategory> categoryList;

	private BudgetCategory editCategory;
	private BudgetCategory beforeEditCategory;
	private Utility utilities = new Utility();

	@FXML
	private TextField selectedCategoryName;

	@FXML
	private TextField selectedCategoryBudget;

	@FXML
	private Label editUserMessage;
	
	/**
	 * switches back to BudgetAppView.fxml view
	 * @param event click event
	 */
	@FXML
	void switchToMainScene(ActionEvent event) {
		refreshCategoryData.emptyUserMessage();
		editCategoryStage.setScene(mainScene);
	}
	
	/**
	 * deletes a category and goes back to BudgetAppView.fxml view
	 * @param event click event
	 */
	@FXML
	void deleteCategory(ActionEvent event) {
		editCategory = null;
		refreshCategoryData.updateCategory(editCategory, beforeEditCategory);
		refreshCategoryData.emptyUserMessage();
		editCategoryStage.setScene(mainScene);
	}
	
	/**
	 * updates a categories information and goes back to BudgetAppView.fxml view
	 * will check if all entries are filled
	 * will check all number entries are valid
	 * @param event click event
	 */
	@FXML
	void updateCategory(ActionEvent event) {
		/*
		 *  if else nest: outer nest makes sure all user entries have a value
		 */	
		if (!utilities.checkTextFieldEmpty(selectedCategoryName)
				&& !utilities.checkTextFieldEmpty(selectedCategoryBudget)) {
			String updatedCategoryName = selectedCategoryName.getText();
			boolean updateCategory = utilities.checkBudgetCategoryDuplication(categoryList, updatedCategoryName);
			// dont update category if the names equal
			if (updatedCategoryName.equals(beforeEditCategory.getName())) {
				updateCategory = false;
			}
			if (!updateCategory) {
				editCategory.setName(updatedCategoryName);
				//check the number entry is valid
				if (utilities.properNumberEntry(selectedCategoryBudget.getText())) {
					editCategory.setMaxBudget(Double.parseDouble(selectedCategoryBudget.getText()));
					refreshCategoryData.updateCategory(editCategory, beforeEditCategory);
					refreshCategoryData.emptyUserMessage();
					editCategoryStage.setScene(mainScene);
				} else {
					editUserMessage.setText("Please enter valid price. Positive integer or decimal numbers only");
				}
			} else {
				editUserMessage.setText("Category already exists,please try another category name");
			}
		} else {
			editUserMessage.setText("Please enter all fields before saving edits");
		}
	}
	
	/**
	 * 
	 * @return the current category being edited
	 */
	public BudgetCategory getEditCategory() {
		return editCategory;
	}
	
	/**
	 * sets the current edited category
	 * @param editCategory the category to edit
	 */
	public void setEditCategory(BudgetCategory editCategory) {
		this.editCategory = editCategory;
	}
	
	/**
	 * sets text fields on the screen
	 * @param categoryToEdit BudgetCategory information to set the fields from
	 */
	public void setViewFields(BudgetCategory categoryToEdit) {
		selectedCategoryName.setText(categoryToEdit.getName());
		selectedCategoryBudget.setText(Double.toString(categoryToEdit.getMaxBudget()));
	}
	
	/**
	 * saves the category before editing is done
	 * @param beforeEditCategory BudgetCategory before it was edited
	 */
	public void setBeforeEditCategory(BudgetCategory beforeEditCategory) {
		this.beforeEditCategory = beforeEditCategory;
	}

}
