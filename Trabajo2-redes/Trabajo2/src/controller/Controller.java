package controller;
/**
 * Sample Skeleton for 'Client&ServerGui.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.text.TextFlow;

public class Controller implements Initializable{

    @FXML // fx:id="comboboxTransaction"
    private ComboBox<?> comboboxTransaction; // Value injected by FXMLLoader

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

    @FXML
    void loadFile(ActionEvent event) {
    	System.out.println("Hola");
    }

    @FXML
    void transaction(ActionEvent event) {

    }

    @FXML
    void updateTransaction(ScrollEvent event) {
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

}
