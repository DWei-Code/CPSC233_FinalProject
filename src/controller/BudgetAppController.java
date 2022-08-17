package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.BiWeeklyItem;
import models.BudgetCategory;
import models.BudgetCategoryTableView;
import models.ExpenseItem;
import models.FileUtility;
import models.MakeTableView;
import models.OneTimeItem;
import models.Utility;
import models.WeeklyItem;

/**
 * Controller class for controlling UI from BudgetAppView.fxml
 * 
 * @author yunwei
 *
 */
public class BudgetAppController {
	@SuppressWarnings("exports")
	public Stage applicationStage;
	// table view for categories
	//private MakeTableView budgetCategoryTable = new MakeTableView("budgetTable");
	private MakeTableView budgetCategoryTable = new BudgetCategoryTableView();
	private ArrayList<BudgetCategory> categories = new ArrayList<BudgetCategory>();
	private Utility utilities = new Utility();
	private FileUtility handleFile = new FileUtility();

	@FXML
	private Label userMessage;

	@FXML
	private VBox rootVbox;

	@FXML
	private HBox categoryTableHBox;

	@FXML
	private TextField budgetCategoryName;

	@FXML
	private ChoiceBox<String> categoryChoiceBox;

	@FXML
	private ChoiceBox<String> paymentTypeChoiceBox;

	@FXML
	private TextField monthlyCategoryBudget;

	@FXML
	private TextField expenseItemCost;

	@FXML
	private TextField expenseItemName;

	/**
	 * upon clicking Add Category button, create a new BudgetCategory with user
	 * entries and 2 buttons and add them to a row in a table created by
	 * MakeTableView class.
	 * 
	 * @param event on button click event
	 */
	@FXML
	void addCategory(ActionEvent event) {
		if (!utilities.checkTextFieldEmpty(budgetCategoryName)
				&& !utilities.checkTextFieldEmpty(monthlyCategoryBudget)) {
			String categoryName = budgetCategoryName.getText();
			String categoryBudget = monthlyCategoryBudget.getText();
			if (utilities.properNumberEntry(categoryBudget)) {
				double categoryBudgetNumber = Double.parseDouble(categoryBudget);
				// System.out.println("Category Added: " + categoryName + " Budget:" +
				// categoryBudget);

				// unique budget categories only, if name repeats do not allow user to add.
				boolean makeNewCategory = utilities.checkBudgetCategoryDuplication(categories, categoryName);
				if (!makeNewCategory) {
					BudgetCategory newCategory = new BudgetCategory(categoryName, categoryBudgetNumber);
					categories.add(newCategory);

					updateTable(newCategory);
					updateChoiceBox();
				} else {
					userMessage.setText("Category already exists,please add unique category only!");
				}
			} else {
				userMessage.setText("Please enter valid budget. Positive integer or decimal numbers only");
			}
		} else {
			userMessage.setText("Please enter all fields for adding category");
		}
	}

	/**
	 * switches the scene to EditCategoryView.fxml where the user can edit a
	 * specific BudgetCategory passes control over to EditCategoryController class
	 * 
	 * @param categoryToEdit the category the user selected to edit
	 * @throws FileNotFoundException when EditCategoryView.fxml is not found
	 * @throws IOException           when having errors accessing
	 *                               EditCategoryView.fxml
	 */
	private void showEditView(BudgetCategory categoryToEdit) throws FileNotFoundException, IOException {
		FXMLLoader loader = new FXMLLoader();
		VBox root = loader.load(new FileInputStream("src/application/EditCategoryView.fxml"));
		EditCategoryController controller = (EditCategoryController) loader.getController();
		controller.mainScene = applicationStage.getScene();
		controller.editCategoryStage = this.applicationStage;
		controller.categoryList = this.categories;
		controller.setEditCategory(categoryToEdit);
		controller.setBeforeEditCategory(categoryToEdit);
		controller.setViewFields(categoryToEdit);
		controller.refreshCategoryData = this;

		Scene scene = new Scene(root, 400, 300);

		applicationStage.setScene(scene);
		applicationStage.setTitle("Budget Tracker");
		applicationStage.show();
	}

	/**
	 * switches the scene to EditCategoryView.fxml where the user can see a table of
	 * items belonging to this category. Passes control over to
	 * CategoryDetailsController class
	 * 
	 * @param categoryToShow
	 * @throws FileNotFoundException when CategoryDetailView.fxml is not found
	 * @throws IOException           when having errors accessing
	 *                               CategoryDetailView.fxml
	 */
	private void showDetailView(BudgetCategory categoryToShow) throws FileNotFoundException, IOException {
		FXMLLoader loader = new FXMLLoader();
		VBox root = loader.load(new FileInputStream("src/application/CategoryDetailView.fxml"));
		CategoryDetailsController controller = (CategoryDetailsController) loader.getController();
		controller.mainScene = applicationStage.getScene();
		controller.categoryDetailStage = this.applicationStage;
		controller.setSelectedCategory(categoryToShow);
		controller.showItemsTable();
		controller.refreshCategoryData = this;

		Scene scene = new Scene(root, 600, 500);

		applicationStage.setScene(scene);
		applicationStage.setTitle("Budget Tracker");
		applicationStage.show();
	}

	/**
	 * updates the choice box for adding items every time a new BugetCategory is
	 * added
	 */
	private void updateChoiceBox() {
		ArrayList<String> categoryNames = new ArrayList<String>();
		for (BudgetCategory bc : categories) {
			categoryNames.add(bc.getName());
		}
		categoryChoiceBox.setItems(FXCollections.observableArrayList(categoryNames));
	}

	/**
	 * Every time a new BugetCategory is added, this is called to update the table
	 * on the screen
	 * 
	 * @param newCategory     the new category added
	 * @param setEditAction
	 * @param setDetailAction
	 */
	private void updateTable(BudgetCategory newCategory) {
		// add new table if it does not exist otherwise add items to table
		categoryTableHBox.setVisible(true);
		categoryTableHBox.setPrefHeight(200);
		categoryTableHBox.setPrefWidth(700);
//--		if (!categoryTableHBox.getChildren().contains(budgetCategoryTable.getBudgetCategoryTable())) {
//--		categoryTableHBox.getChildren().add(0, budgetCategoryTable.getBudgetCategoryTable());
		if (!categoryTableHBox.getChildren().contains(budgetCategoryTable.getTable())) {
			categoryTableHBox.getChildren().add(0, budgetCategoryTable.getTable());
		}
		//-----budgetCategoryTable.updateBudgetTable(newCategory);
		budgetCategoryTable.updateTable(newCategory);
		// budgetCategoryTable.addEditButton(setEditAction);
		// budgetCategoryTable.addDetailButton(setDetailAction);

	}

	/**
	 * 
	 * @return an ArrayList of BudgetCategory currently added
	 */
	public ArrayList<BudgetCategory> getCategories() {
		return categories;
	}

	/**
	 * empties this screens user message
	 */
	public void emptyUserMessage() {
		userMessage.setText("");
	}

	/**
	 * when user updates category in EditCategoryController, saves the changes and
	 * clicks done, this is called to persist the changes by replacing the category
	 * information in the instance variable ArrayList "categories" and updating the
	 * table.
	 * 
	 * @param editedCategory     the newly edited category information
	 * @param beforeEditCategory before it was edited information
	 */
	public void updateCategory(BudgetCategory editedCategory, BudgetCategory beforeEditCategory) {
		// matches on unique Category names an updates or deletes category based on user
		// selection
		boolean updateTable = true;
		//------budgetCategoryTable.clearBudgetTable();
		budgetCategoryTable.clearTable();
		for (int i = 0; i < categories.size(); i++) {
			// System.out.println(categories.get(i).getName());
			// System.out.println(beforeEditCategory.getName());
			if (categories.get(i).getName().equals(beforeEditCategory.getName())) {
				if (editedCategory == null) {
					categories.remove(i);
					updateTable = false;
					break;
				} else {
					updateTable = true;
					categories.set(i, editedCategory);
				}
			}
			if (updateTable) {
				//--budgetCategoryTable.updateBudgetTable(categories.get(i));
				budgetCategoryTable.updateTable(categories.get(i));
			}

		}
		updateChoiceBox();
	}

	/**
	 * Adds ExpenseItem under a BugdetCategory and calculates the available budget
	 * left that was set for the category
	 * 
	 * @param event button click event
	 */
	@FXML
	void addItem(ActionEvent event) {
		if (!utilities.checkTextFieldEmpty(expenseItemName) && !utilities.checkTextFieldEmpty(expenseItemCost)
				&& utilities.isStringChoiceboxSelected(categoryChoiceBox)
				&& utilities.isStringChoiceboxSelected(paymentTypeChoiceBox)) {
			String categoryChoiceBoxSelected = categoryChoiceBox.getValue();
			String paymentChoiceBoxSelected = paymentTypeChoiceBox.getValue();
			String itemName = expenseItemName.getText();
			String expenseItemPrice = expenseItemCost.getText();
			if (utilities.properNumberEntry(expenseItemPrice)) {
				double itemPrice = Double.parseDouble(expenseItemPrice);

				// System.out.println(
				// "Item Added: " + itemName + " Price:" + itemPrice + " Choice Box: " +
				// categoryChoiceBoxSelected);

				ExpenseItem newItem = null;
				// switch for determining which subclass of item to make depending on user
				// choice
				switch (paymentChoiceBoxSelected) {
				case "One Time":
					newItem = new OneTimeItem(itemName, itemPrice);
					break;
				case "Weekly":
					newItem = new WeeklyItem(itemName, itemPrice);
					break;
				case "Bi-Weekly":
					newItem = new BiWeeklyItem(itemName, itemPrice);
					break;
				default:
					break;

				}

				//--budgetCategoryTable.clearBudgetTable();
				budgetCategoryTable.clearTable();
				for (BudgetCategory bc : categories) {
					if (bc.getName().equals(categoryChoiceBoxSelected)) {
						bc.getListOfItems().add(newItem);
						userMessage.setText(bc.updateBudget(newItem));
					}
					//--budgetCategoryTable.updateBudgetTable(bc);
					budgetCategoryTable.updateTable(bc);
				}
			} else {
				userMessage.setText("Please enter valid price. Positive integer or decimal numbers only");
			}

		} else {
			userMessage.setText(
					"Please enter all item fields to add the item. Also a category must be added to add an item");
		}
	}

	@FXML
	void goToEditCategoryStage(ActionEvent event) throws FileNotFoundException, IOException {
		BudgetCategory selectedCategory = (BudgetCategory) budgetCategoryTable.getData();
				//budgetCategoryTable.getBudgetCategoryData();
		if (!utilities.isNull(selectedCategory)) {
			this.showEditView(selectedCategory);
		} else {
			userMessage.setText("Please click on a row to select which category to edit");
		}
	}

	@FXML
	void goToCategoryDetailStage(ActionEvent event) throws FileNotFoundException, IOException {
		BudgetCategory selectedCategory = (BudgetCategory) budgetCategoryTable.getData();
				//budgetCategoryTable.getBudgetCategoryData();
		if (!utilities.isNull(selectedCategory)) {
			this.showDetailView(selectedCategory);
		} else {
			userMessage.setText("Please click on a row to select which category to view details of");
		}
	}

	@FXML
	void saveData(ActionEvent event) {
		userMessage.setText(
			handleFile.saveDataToFile("./res/BudgetData.dat", "./res/MonthlyBudget.txt", categories));
	}

	@FXML
	void loadSavedData(ActionEvent event) {
		this.categories = handleFile.loadSavedData("./res/BudgetData.dat");
		if(this.categories == null) {
			userMessage.setText("No previous saved data, cannot load the file");
		}else {
			//-- budgetCategoryTable.clearBudgetTable();
			budgetCategoryTable.clearTable();
			for (BudgetCategory categoryToLoad : categories) {
				this.updateTable(categoryToLoad);
			}
			this.updateChoiceBox();
		}
	}

}
