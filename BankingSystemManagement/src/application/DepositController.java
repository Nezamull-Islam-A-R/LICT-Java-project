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

public class DepositController implements Initializable{

    @FXML
    private JFXTextField dAccId;

    @FXML
    private JFXTextField dAccPin;

    @FXML
    private JFXTextField dMoney;

    @FXML
    private JFXButton depositMoney;
    
    @FXML
    private Label depositMsg;

    @FXML
    private JFXComboBox<String> dAccType;
    ObservableList<String> list = FXCollections.observableArrayList("Savings Account","Current Account");
    //--------Variables
    private String accNo,accPin,moneyD,accType;

    @FXML
    void handleDepositAcc(ActionEvent event) {
    	System.out.println("Acc. Type : "+dAccType.getValue());
    }
    
    ConnMyDb conn = new ConnMyDb();
    int flagDep = 0;
    @FXML
    void handleDepositMoneyClick(ActionEvent event) {
    	initVariables();
    	flagDep = conn.depositMoneySQL(accNo,accPin,moneyD,accType);
    	if(flagDep == 1)
    		depositMsg.setText("Deposit successful ");
    	else
    		depositMsg.setText("Failed , Try again.");
    }
    
    private void initVariables() {
		// TODO Auto-generated method stub
		try{
			accNo = dAccId.getText().toString();
			accPin = dAccPin.getText().toString();
			moneyD = dMoney.getText().toString();
			accType = dAccType.getValue().toString();
		}catch(Exception e){
			depositMsg.setText("Invalid input or "+e.getMessage());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		dAccType.setItems(list);
	}

}
