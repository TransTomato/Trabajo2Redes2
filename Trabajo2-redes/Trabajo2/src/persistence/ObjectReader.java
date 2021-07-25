package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ObjectReader {

	private static String folder = "src/files/";
	public static ArrayList<String> readTransactions(String fileName) {
		ArrayList<String> transactions = new ArrayList<String>(); 
		try {
		      File myObj = new File(folder+fileName);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        transactions.add(data);
		        //System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return transactions;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readTransactions("transactions_example.txt");
	}

}
