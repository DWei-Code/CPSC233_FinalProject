package models;

import java.io.*;
import java.util.ArrayList;
/**
 * Class used to handle saving object data to file,
 * saving object data as text file, and loading object data back into the application from file.
 * @author yunwei
 *
 */
public class FileUtility {
	
	/**
	 * used to save objects to file (.dat) and to call saveDataToTextFile
	 * to save data as a readable text file.
	 * @param dataSaveLocation file path to save objects to
	 * @param textFileSaveLocation file path to save text file to
	 * @param budgetData ArrayList of BudgetCategory objects to save
	 * @return user message as String to notify the user if the file was saved or not
	 */
	public String saveDataToFile(String dataSaveLocation, String textFileSaveLocation,
			ArrayList<BudgetCategory> budgetData) {
		String userMessage = "Your data has been saved!";
		try {
			FileOutputStream fos = new FileOutputStream(dataSaveLocation);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(budgetData);
			oos.close();
			
			// save to text file as well so user can view it
			saveDataToTextFile(textFileSaveLocation, budgetData);
			
		} catch (IOException e) {
			e.printStackTrace();
			userMessage = "saving data ran into an error!";
		}

		return userMessage;
	}
	
	/**
	 * used to save object data as text to a text file
	 * @param textFileSaveLocation text file location
	 * @param budgetData ArrayList of BudgetCategory objects to save as text
	 */
	public void saveDataToTextFile(String textFileSaveLocation, ArrayList<BudgetCategory> budgetData) {
		try {
			PrintWriter outToText = null;
			outToText = new PrintWriter(new FileWriter(textFileSaveLocation));
			for (BudgetCategory categoryToSave : budgetData) {
				outToText.println(categoryToSave.toString());
			}
			outToText.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * used to load objects saved to a .dat file if the file exists
	 * @param dataFileLocation the file path to find the file to load objects from
	 * @return ArrayList of BudgetCategory objects
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<BudgetCategory> loadSavedData(String dataFileLocation) {
		ArrayList<BudgetCategory> savedData = null;
		try {
			File dataFile = new File(dataFileLocation);
			if (dataFile.exists() && dataFile.isFile()) {
				FileInputStream fis = new FileInputStream(dataFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				savedData = (ArrayList<BudgetCategory>) ois.readObject();

				ois.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return savedData;
	}
}
