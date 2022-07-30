package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BudgetAppController {

    @FXML
    private TextField budgetCategoryName;

    @FXML
    private TextField monthlyCategoryBudget;

    @FXML
    void addCategory(ActionEvent event) {
    	System.out.print("Category Added");
    }

}
