package models;

import java.util.ArrayList;

public class Utility {
	
	/**
	 * Used to make sure all categories added have unique names so there is no duplicate categories
	 * @param allCategories current list of added categories
	 * @param categoryName to be added or edited category name
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
}
