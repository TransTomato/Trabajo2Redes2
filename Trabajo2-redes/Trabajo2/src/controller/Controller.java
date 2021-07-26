package controller;
/**
 * Sample Skeleton for 'Client&ServerGui.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.text.TextFlow;
import model.BankOptions;

public class Controller implements Initializable{

    @FXML // fx:id="comboboxTransaction"
    private ComboBox<String> comboboxTransaction; // Value injected by FXMLLoader

    @FXML // fx:id="textInput1"
    private TextField textInput1; // Value injected by FXMLLoader

    @FXML // fx:id="label1"
    private Label label1; // Value injected by FXMLLoader

    @FXML // fx:id="textInput2"
    private TextField textInput2; // Value injected by FXMLLoader

    @FXML // fx:id="label2"
    private Label label2; // Value injected by FXMLLoader

    @FXML // fx:id="fileButton"
    private Button fileButton; // Value injected by FXMLLoader

    @FXML // fx:id="transactionButton"
    private Button transactionButton; // Value injected by FXMLLoader

    @FXML // fx:id="clientConsole"
    private TextFlow clientConsole; // Value injected by FXMLLoader

    @FXML // fx:id="serverConsole"
    private TextFlow serverConsole; // Value injected by FXMLLoader
    
    private ObservableList<String> optionsTransaction = FXCollections.observableArrayList();

    @FXML
    void loadFile(ActionEvent event) {
    	System.out.println("Hola");
    }

    @FXML
    void transaction(ActionEvent event) {

    }

    @FXML
    void updateTransaction(ScrollEvent event) {
    	System.out.println("holi");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		for (BankOptions op : BankOptions.values()) 
			optionsTransaction.add(op.toString().toLowerCase().replace("_", " "));
		comboboxTransaction.setItems(optionsTransaction);
		
	}

}
