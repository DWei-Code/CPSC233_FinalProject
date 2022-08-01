module FinalProject_BudgettingApp {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	
	// error fixing reference: 
	// https://stackoverflow.com/questions/67372505/java-lang-illegalaccessexception-module-javafx-base-cannot-access-class-sample
	opens models to javafx.fxml;
	opens controller to javafx.fxml;
	//opens models.BudgetCategory to javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
	
	exports models;
	exports controller;
}
