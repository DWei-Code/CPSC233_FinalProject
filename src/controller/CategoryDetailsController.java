package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.BiWeeklyItem;
import models.BudgetCategory;
import models.ExpenseItem;
import models.ExpenseItemTableView;
import models.MakeTableView;
import models.OneTimeItem;
import models.Utility;
import models.WeeklyItem;

/**
 * controller class for controlling UI for CategoryDetailView.fxml
 * 
 * @author yunwei
 *
 */
public class CategoryDetailsController {

	@SuppressWarnings("exports")
	public Stage categoryDetailStage;
	@SuppressWarnings("exports")
	public Scene mainScene;
	public BudgetAppController refreshCategoryData;

	private BudgetCategory selectedCategory;
	//private MakeTableView itemsTable = new MakeTableView("itemTable");
	private MakeTableView itemsTable = new ExpenseItemTableView();
	private Utility utilities = new Utility();

	@FXML
	private Label userMessage;

	@FXML
	private Label categoryNameTextField;
	
	@FXML
    private Label categoryBudgetLeft;

	@FXML
	private TextField itemNameTextField;

	@FXML
	private TextField itemPriceTextField;

	@FXML
	private TextField selectedItemId;

	@FXML
	private VBox detailsVbox;
	
	@FXML
    private HBox itemsTableHBox;

	@FXML
	private ChoiceBox<String> paymentTypeChoiceBox;

	/**
	 * switches scene back to main screen
	 * 
	 * @param event button click event
	 */
	@FXML
	void sceneSwitch(ActionEvent event) {
		refreshCategoryData.updateCategory(selectedCategory, selectedCategory);
		refreshCategoryData.emptyUserMessage();
		categoryDetailStage.setScene(mainScene);
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
		itemsTableHBox.getChildren().add(0, itemsTable.getTable());
		itemsTable.clearTable();
		for (ExpenseItem ei : selectedCategory.getListOfItems()) {
			itemsTable.updateTable(ei);
		}
		categoryNameTextField.setText(selectedCategory.getName());
		categoryBudgetLeft.setText(selectedCategory.getBudgetLeft() + "");
	}

	/**
	 * used for updating this categories items table after an edit is saved
	 */
	public void updateItemsTable() {
		itemsTable.clearTable();
		for (ExpenseItem ei : selectedCategory.getListOfItems()) {
			itemsTable.updateTable(ei);
		}
		categoryBudgetLeft.setText(selectedCategory.getBudgetLeft() + "");
	}
	
	/**
	 * sets the name, price and id of the item into the text fields
	 * @param selectedItem the chosen item
	 */
	public void setLabels(ExpenseItem selectedItem) {
		if (selectedItem != null) {
			itemNameTextField.setText(selectedItem.getName());
			itemPriceTextField.setText(selectedItem.getPrice() + "");
			selectedItemId.setText(selectedItem.getId() + "");
		}
	}
	
	/**
	 * deletes the selected item within the table
	 * @param event click event
	 */
	@FXML
    void deleteSelectedItem(ActionEvent event) {
		/*
		 *  if else nest: outer nest makes sure a row is selected within the table
		 *  	inner if: finds the item by id to delete it
		 */	
		ExpenseItem selectedItem = (ExpenseItem) itemsTable.getData();
		if (!utilities.isNull(selectedItem)) {
			for (int i = 0; i < selectedCategory.getListOfItems().size(); i++) {
				if (selectedCategory.getListOfItems().get(i).getId() == selectedItem.getId()) {
					selectedCategory.getListOfItems().remove(i);
				}
			}
			selectedCategory.addBudget(selectedItem);
			cancelEdit(event);
			updateItemsTable();
		}else {
			userMessage.setText("Please click on a row to select which item to delete");
		}
    }
	
	/**
	 * when edit item button is clicked the selected items data will populate in the field to edit 
	 * @param event click event
	 */
	@FXML
	void editItem(ActionEvent event) {
		/*
		 *  if else nest: outer nest makes sure a row is selected within the table
		 *  	inner if else: checks what instance the item is of to set the choice box
		 */	
		ExpenseItem selectedItem = (ExpenseItem) itemsTable.getData();
		if (!utilities.isNull(selectedItem)) {
			setLabels(selectedItem);
			if (selectedItem instanceof OneTimeItem) {
				paymentTypeChoiceBox.setValue("One Time");
			} else if (selectedItem instanceof WeeklyItem) {
				paymentTypeChoiceBox.setValue("Weekly");
			} else {
				paymentTypeChoiceBox.setValue("Bi-Weekly");
			}
		} else {
			userMessage.setText("Please click on a row to select which item to edit");
		}
	}
	
	/**
	 * controls the save button on the screen to save item edits
	 * checks all fields are filled and number entry is valid
	 * @param event click event
	 */
	@FXML
	void saveItemEdit(ActionEvent event) {
		/*
		 *  if else nest: outer nest makes sure all user entries have a value
		 *  	inner if else: checks to make sure the number entry is a valid positive integer or double
		 */	
		if (!utilities.checkTextFieldEmpty(itemNameTextField) && !utilities.checkTextFieldEmpty(itemPriceTextField)
				&& utilities.isStringChoiceboxSelected(paymentTypeChoiceBox)) {
			String itemName = itemNameTextField.getText();
			String expenseItemPrice = itemPriceTextField.getText();
			if (utilities.properNumberEntry(expenseItemPrice)) {
				double itemPrice = Double.parseDouble(expenseItemPrice);

				String paymentChoiceBoxSelected = paymentTypeChoiceBox.getValue();

				ExpenseItem editedItem = null;
				// switch for determining which subclass of item to make depending on user
				// choice
				switch (paymentChoiceBoxSelected) {
				case "One Time":
					editedItem = new OneTimeItem(itemName, itemPrice);
					break;
				case "Weekly":
					editedItem = new WeeklyItem(itemName, itemPrice);
					break;
				case "Bi-Weekly":
					editedItem = new BiWeeklyItem(itemName, itemPrice);
					break;
				default:
					userMessage.setText("Please select item payment type!");

				}
				ExpenseItem beforeEdit = null;
				for (int i = 0; i < selectedCategory.getListOfItems().size(); i++) {
					if (selectedCategory.getListOfItems().get(i).getId() == Integer
							.parseInt(selectedItemId.getText())) {
						beforeEdit = selectedCategory.getListOfItems().get(i);
						selectedCategory.getListOfItems().set(i, editedItem);
					}
				}
				// add the budget of the item before editing before calculating new budget
				selectedCategory.addBudget(beforeEdit);
				selectedCategory.updateBudget(editedItem);
				cancelEdit(event);
				updateItemsTable();
				userMessage.setText("Table updated");
			} else {
				userMessage.setText("Please enter valid price. Positive integer or decimal numbers only");
			}
		} else {
			userMessage.setText("Please enter all fields before saving the edits!");
		}
	}
	
	/**
	 * clears all field and table selections
	 * @param event click event
	 */
	@FXML
	void cancelEdit(ActionEvent event) {
		itemNameTextField.setText("");
		itemPriceTextField.setText("");
		paymentTypeChoiceBox.setValue("");
		itemsTable.clearSelection();
		
	}
}
