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

public class CheckUsersBalancInf implements Initializable{

    @FXML
    private JFXTextField chAccId;

    @FXML
    private JFXTextField chAccPin;

    @FXML
    private Label chTextShow;

    @FXML
    private JFXButton checkBal;

    @FXML
    private JFXComboBox<String> chAccT;
    ObservableList<String> list = FXCollections.observableArrayList("Savings Account","Current Account");
    
    private String accId,accPin,accType;
    @FXML
    void handleCheckBal(ActionEvent event) {
    	int flagBal = 0;
    	int totalBal = 0;
    	flagBal = initVariables();
    	if(flagBal == 1){
    		ConnMyDb myCon = new ConnMyDb();
    		totalBal = myCon.showUpBalance(accId,accPin,accType);
    		chTextShow.setText("Your Current Balance is : "+totalBal);
    	}
    }
    
    private int initVariables() {
		// TODO Auto-generated method stub
    	int y = 1;
    	try{
    		accId = chAccId.getText().toString();
    		accPin = chAccPin.getText().toString();
    		accType = chAccT.getValue().toString();
    	}catch(Exception e){
    		System.out.println("input invalid "+e.getMessage());
    		chTextShow.setText("Invalid input or "+e.getMessage());
    		y = 0;
    	}
		return y;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		chAccT.setItems(list);
	}

}
