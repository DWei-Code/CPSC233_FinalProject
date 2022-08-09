package controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.BudgetCategory;
import models.Utility;

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

	@FXML
	void switchToMainScene(ActionEvent event) {
		editCategoryStage.setScene(mainScene);
	}

	@FXML
	void deleteCategory(ActionEvent event) {
		editCategory = null;
		refreshCategoryData.updateCategory(editCategory, beforeEditCategory);
		editCategoryStage.setScene(mainScene);
	}

	@FXML
	void updateCategory(ActionEvent event) {
		String updatedCategoryName = selectedCategoryName.getText();
		boolean updateCategory = utilities.checkBudgetCategoryDuplication(categoryList, updatedCategoryName);
		if (!updateCategory) {
			editCategory.setName(selectedCategoryName.getText());
			editCategory.setMaxBudget(Double.parseDouble(selectedCategoryBudget.getText()));
			refreshCategoryData.updateCategory(beforeEditCategory, editCategory);
			// editUserMessage.setText("Category updated!");
			editCategoryStage.setScene(mainScene);
		} else {
			editUserMessage.setText("Category already exists,please try another category name");
		}
	}

	// https://stackoverflow.com/questions/22166610/how-to-create-a-popup-window-in-javafx
	// maybe implement later
	@FXML
	void popUp(ActionEvent event) {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(editCategoryStage);
		VBox dialogVbox = new VBox(20);
		dialogVbox.getChildren().add(new Text("This is a Dialog"));
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		dialog.setScene(dialogScene);
		dialog.show();
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
		this.beforeEditCategory = new BudgetCategory (beforeEditCategory);
	}

}
