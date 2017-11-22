package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class WIthDrawCash implements Initializable{

    @FXML
    private JFXTextField wAccNo;

    @FXML
    private JFXTextField wAccPass;

    @FXML
    private JFXTextField wAmaount;

    @FXML
    private Label wShowText;

    @FXML
    private JFXButton withdrawMoney;
    
    @FXML
    private JFXComboBox<String> wSelectAcc;
    ObservableList<String> list = FXCollections.observableArrayList("Savings Account","Current Account");
    
    private String accNo,accPass,amount,seectType;
    
    @FXML
    void handleWithDrawMoney(ActionEvent event) {
    	int flagW = 0;
    	flagW = initVariables();
    	ConnMyDb myCon = new ConnMyDb();
    	int flagSQl = 0;
    	if(flagW == 1){
    		flagSQl = myCon.withDrawMoneyHandleSql(accNo,accPass,amount,seectType);
    		
    		wShowText.setText("Money has been successfully withdrawn");
    	}else{
    		wShowText.setText("Invalid Input, Try Again");
    	}
    }
    
    private int initVariables() {
		// TODO Auto-generated method stub
    	int flagVar = 0;
    	try{
    		accNo = wAccNo.getText().toString();
    		accPass = wAccPass.getText().toString();
    		amount = wAmaount.getText().toString();
    		seectType = wSelectAcc.getValue().toString();
    		flagVar = 1;
    	}catch(Exception e){
    		wShowText.setText("Invalid Input, Try Again");
    	}
		return(flagVar);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		wSelectAcc.setItems(list);
	}
}
