package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
import models.ExpenseItem;
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
	private MakeTableView budgetCategoryTable = new MakeTableView("budgetTable");
	private ArrayList<BudgetCategory> categories = new ArrayList<BudgetCategory>();
	private Utility utilities = new Utility();

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
					/*
					 * Button setDetailAction = new Button("View Details");
					 * setDetailAction.setOnAction(detailsEvent -> { try {
					 * showDetailView(newCategory); } catch (IOException e1) { e1.printStackTrace();
					 * } }); newCategory.setDetailsButton(setDetailAction);
					 * 
					 * Button setEditAction = new Button("Edit Category");
					 * setEditAction.setOnAction(detailsEvent -> { try { showEditView(newCategory);
					 * } catch (IOException e) { e.printStackTrace(); } });
					 * newCategory.setEditButton(setEditAction);
					 */

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
		if (!categoryTableHBox.getChildren().contains(budgetCategoryTable.getBudgetCategoryTable())) {
			categoryTableHBox.getChildren().add(0, budgetCategoryTable.getBudgetCategoryTable());
		}
		budgetCategoryTable.updateBudgetTable(newCategory);
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
		budgetCategoryTable.clearBudgetTable();
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
				budgetCategoryTable.updateBudgetTable(categories.get(i));
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

				budgetCategoryTable.clearBudgetTable();
				for (BudgetCategory bc : categories) {
					if (bc.getName().equals(categoryChoiceBoxSelected)) {
						bc.getListOfItems().add(newItem);
						userMessage.setText(bc.updateBudget(newItem));
					}
					budgetCategoryTable.updateBudgetTable(bc);
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
		BudgetCategory selectedCategory = budgetCategoryTable.getBudgetCategoryData();
		if (!utilities.isNull(selectedCategory)) {
			this.showEditView(selectedCategory);
		} else {
			userMessage.setText("Please click on a row to select which category to edit");
		}
	}

	@FXML
	void goToCategoryDetailStage(ActionEvent event) throws FileNotFoundException, IOException {
		BudgetCategory selectedCategory = budgetCategoryTable.getBudgetCategoryData();
		if (!utilities.isNull(selectedCategory)) {
			this.showDetailView(selectedCategory);
		} else {
			userMessage.setText("Please click on a row to select which category to view details of");
		}
	}

	@FXML
	void saveData(ActionEvent event) {
		try {
			FileOutputStream fos = new FileOutputStream("./res/BudgetData.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			// for(BudgetCategory categoryToSave : categories) {
			// BudgetCategory category = new BudgetCategory("test", 2);
			oos.writeObject(categories);
			// }
			oos.close();
			userMessage.setText("Your data has been saved!");

			PrintWriter outToText = null;
			outToText = new PrintWriter(new FileWriter("./res/MonthlyBudget.txt"));
			for (BudgetCategory categoryToSave : categories) {
				outToText.println(categoryToSave.toString());
			}
			outToText.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void loadSavedData(ActionEvent event) {
		try {
			File dataFile = new File("./res/BudgetData.dat");
			if (dataFile.exists() && dataFile.isFile()) {
				FileInputStream fis = new FileInputStream(dataFile);
				ObjectInputStream ois = new ObjectInputStream(fis);

				// for(BudgetCategory categoryToSave : categories) {
				// BudgetCategory category = new BudgetCategory("test", 2);
				@SuppressWarnings("unchecked")
				ArrayList<BudgetCategory> savedData = (ArrayList<BudgetCategory>) ois.readObject();
				// }
				ois.close();
				// userMessage.setText("Your data has been saved!");
				this.categories = savedData;
				budgetCategoryTable.clearBudgetTable();
				for (BudgetCategory categoryToLoad : categories) {
					this.updateTable(categoryToLoad);
				}
				this.updateChoiceBox();
			} else {
				userMessage.setText("No previous saved data, cannot load the file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
