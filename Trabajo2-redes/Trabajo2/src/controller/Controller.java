package controller;
/**
 * Sample Skeleton for 'Client&ServerGui.fxml' Controller Class
 */

import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import connectionTCP.EchoTCPClient;
import connectionTCP.EchoTCPClientProtocol;
import connectionTCP.EchoTCPServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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

    private Thread t1;
    private EchoTCPServer es;
    private EchoTCPClient ec;
    
    
    @FXML
    void loadFile(ActionEvent event) {
    	ec.createSocket();
		String consoleC = EchoTCPClientProtocol.loadFile(ec.clientSideSocket);
		Text t = new Text(consoleC);
		clientConsole.getChildren().add(t);
    }

    @FXML
    void transaction(ActionEvent event) {
    	String consoleC ="",
    			name = "",
    			account = "",
    			pocket = "",
    			value = "";
    	Text t = null;
    	String option = comboboxTransaction.getValue().toUpperCase().replace(" ", "_");
    	switch (BankOptions.valueOf(option)) {
		case ABRIR_CUENTA:
			name = textInput1.getText();
			ec.createSocket();
			consoleC = EchoTCPClientProtocol.createAccount(ec.clientSideSocket, name);
			t = new Text(consoleC);
			clientConsole.getChildren().add(t);
		break;
		case ABRIR_BOLSILLO:
			account = textInput1.getText();
			ec.createSocket();
			consoleC = EchoTCPClientProtocol.createPocket(ec.clientSideSocket, account);
			t = new Text(consoleC);
			clientConsole.getChildren().add(t);
		break;
		case CANCELAR_BOLSILLO:
			pocket = textInput1.getText();
			ec.createSocket();
			consoleC = EchoTCPClientProtocol.terminatePocket(ec.clientSideSocket, pocket);
			t = new Text(consoleC);
			clientConsole.getChildren().add(t);
		break;
		case CANCELAR_CUENTA:
			account = textInput1.getText();
			ec.createSocket();
			consoleC = EchoTCPClientProtocol.terminateAccount(ec.clientSideSocket, account);
			t = new Text(consoleC);
			clientConsole.getChildren().add(t);
	 	break;
	 	case DEPOSITAR:
	 		account = textInput1.getText();
	 		value = textInput2.getText();
			ec.createSocket();
			consoleC = EchoTCPClientProtocol.deposit(ec.clientSideSocket, account, value);
			t = new Text(consoleC);
			clientConsole.getChildren().add(t);
	 	break;
	 	case RETIRAR:
	 		account = textInput1.getText();
	 		value = textInput2.getText();
			ec.createSocket();
			consoleC = EchoTCPClientProtocol.withdraw(ec.clientSideSocket, account, value);
			t = new Text(consoleC);
			clientConsole.getChildren().add(t);
		break;
	 	case TRASLADAR:
	 		account = textInput1.getText();
	 		value = textInput2.getText();
			ec.createSocket();
			consoleC = EchoTCPClientProtocol.transfer(ec.clientSideSocket, account, value);
			t = new Text(consoleC);
			clientConsole.getChildren().add(t);
		break;
	 	case CONSULTAR:
	 		account = textInput1.getText();
			ec.createSocket();
			consoleC = EchoTCPClientProtocol.check(ec.clientSideSocket, account);
			t = new Text(consoleC);
			clientConsole.getChildren().add(t);
		break;
	 	case LISTAR_TRANSACCIONES:
			ec.createSocket();
			consoleC = EchoTCPClientProtocol.listTransfers(ec.clientSideSocket);
			t = new Text(consoleC);
			clientConsole.getChildren().add(t);
		break;
		default:
			break;
		}
    	textInput1.setText("");
    	textInput2.setText("");
    }
    

    @FXML
    void updateTransaction(ActionEvent event) {
    	label1.setDisable(false);
    	label2.setDisable(false);
    	textInput1.setDisable(false);
    	textInput2.setDisable(false);
    	transactionButton.setDisable(false);
    	String option = comboboxTransaction.getValue().toUpperCase().replace(" ", "_");
    	switch (BankOptions.valueOf(option)) {
		case ABRIR_CUENTA:
			label1.setText("Nombre completo:");
			label2.setDisable(true);
			textInput2.setDisable(true);
		break;
		case ABRIR_BOLSILLO:
			label1.setText("Numero de cuenta:");
			label2.setDisable(true);
			textInput2.setDisable(true);
		break;
		case CANCELAR_BOLSILLO:
			label1.setText("Numero de bolsillo:");
			label2.setDisable(true);
			textInput2.setDisable(true);
		break;
		case CANCELAR_CUENTA:
			label1.setText("# de cuenta:");
			label2.setDisable(true);
			textInput2.setDisable(true);
	 	break;
	 	case DEPOSITAR:
	 		label1.setText("Numero de cuenta:");
			label2.setText("Valor:");
	 	break;
	 	case RETIRAR:
	 		label1.setText("Numero de cuenta:");
			label2.setText("Valor:");
		break;
	 	case TRASLADAR:
	 		label1.setText("Numero de cuenta:");
			label2.setText("Valor:");
		break;
	 	case CONSULTAR:
	 		label1.setText("Numero de cuenta/bolsillo:");
			label2.setDisable(true);
			textInput2.setDisable(true);
		break;
	 	case LISTAR_TRANSACCIONES:
	 		label1.setDisable(true);
			label2.setDisable(true);
			textInput1.setDisable(true);
			textInput2.setDisable(true);
		break;
		default:
			break;
		}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Initialize the EchoTCPClient and EchoTCPServer
		
		es = new EchoTCPServer();
		t1 = new Thread(es);
		t1.start();
		
		ec = new EchoTCPClient();
		
		
		
		
		
		
		for (BankOptions op : BankOptions.values()) {
			if(op!=BankOptions.CARGAR)
				optionsTransaction.add(op.toString().toLowerCase().replace("_", " "));	
		}
		comboboxTransaction.setItems(optionsTransaction);
		
	}

}
