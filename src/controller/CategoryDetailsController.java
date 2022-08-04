package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.BudgetCategory;
import models.ExpenseItem;
import models.MakeTableView;

public class CategoryDetailsController {

	public Stage applicationStage;
	public Scene mainScene;

	private BudgetCategory selectedCategory;
	private MakeTableView itemsTable = new MakeTableView("itemTable");
	
	@FXML
	private VBox detailsVbox;

	@FXML
	void sceneSwitch(ActionEvent event) {
		applicationStage.setScene(mainScene);
	}

	public BudgetCategory getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(BudgetCategory selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public void showItemsTable() {
		detailsVbox.getChildren().add(0, itemsTable.getItemCategoryTable());
		itemsTable.clearItemTable();
		for (ExpenseItem ei : selectedCategory.getListOfItems()) {
			itemsTable.updateItemTable(ei);
		}
	}
}
