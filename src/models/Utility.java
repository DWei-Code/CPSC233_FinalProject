package models;

import java.util.ArrayList;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Utility {

	/**
	 * Used to make sure all categories added have unique names so there is no
	 * duplicate categories
	 * 
	 * @param allCategories current list of added categories
	 * @param categoryName  to be added or edited category name
	 * @return false if there is no duplicate, true if there is.
	 */
	public boolean checkBudgetCategoryDuplication(ArrayList<BudgetCategory> allCategories, String categoryName) {
		boolean duplicatedCategory = false;
		for (BudgetCategory checkName : allCategories) {
			if (checkName.getName().equals(categoryName)) {
				duplicatedCategory = true;
			}
		}
		return duplicatedCategory;
	}

	/**
	 * checks to see if an object is null
	 * 
	 * @param objectToCheck object that needs to be checked
	 * @return true if object is a null reference other wise false.
	 */
	public boolean isNull(Object objectToCheck) {
		return objectToCheck == null ? true : false;
	}
	
	/**
	 * used to check if string entry is a valid double or int number
	 * @param valueAsString user entered value 
	 * @return true if the entry can be parsed into a double else false
	 * 
	 * reference: code taken from my own grade calculator project worked on during the semester
	 */
	public boolean properNumberEntry(String valueAsString) {
		// assume the entry is valid to begin with
		// check that the string entered by the user is a valid decimal number
		boolean validNumber = true;
		int decimalCount = 0;
		// loop through user entry to check its a decimal number or integer number
		// special characters or double decimals are not allowed
		for (char c : valueAsString.toCharArray()) {
			if (!Character.isDigit(c)) {
				validNumber = false;
				if (c == '.' && decimalCount == 0) {
					decimalCount++;
					validNumber = true;
				}
			}
			// ending the entry with a decimal is not acceptable
			if (valueAsString.charAt(valueAsString.length() - 1) == '.') {
				validNumber = false;
			}
		}
		return validNumber;
	}
	
	@SuppressWarnings("exports")
	public boolean checkTextFieldEmpty(TextField fieldToCheck) {
		return fieldToCheck.getText().isBlank();
	}
	
	public boolean isStringChoiceboxSelected(ChoiceBox<String> boxToCheck) {
		return boxToCheck.getValue() != null;
	}

}
