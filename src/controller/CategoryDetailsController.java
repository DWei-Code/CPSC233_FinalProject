package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.BudgetCategory;
import models.ExpenseItem;
import models.MakeTableView;
/**
 * controller class for controlling UI for CategoryDetailView.fxml
 * @author yunwei
 *
 */
public class CategoryDetailsController {

	@SuppressWarnings("exports")
	public Stage applicationStage;
	@SuppressWarnings("exports")
	public Scene mainScene;

	private BudgetCategory selectedCategory;
	private MakeTableView itemsTable = new MakeTableView("itemTable");
	
    @FXML
    private TextField itemNameTextField;

    @FXML
    private TextField itemPriceTextField;
    
	@FXML
	private VBox detailsVbox;
	
	/**
	 * switches scene back to main screen
	 * @param event button click event
	 */
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
	/**
	 * Generates a table of ExpenItems (using MakeTableView class) that the user 
	 * selected BudgetCategory owns under its instance ArrayList.
	 */
	public void showItemsTable() {
		detailsVbox.getChildren().add(0, itemsTable.getItemCategoryTable());
		itemsTable.clearItemTable();
		for (ExpenseItem ei : selectedCategory.getListOfItems()) {
			itemsTable.updateItemTable(ei);
		}
	}
	
    @FXML
    void saveItemEdit(ActionEvent event) {

    }

    @FXML
    void cancelEdit(ActionEvent event) {

    }
}
