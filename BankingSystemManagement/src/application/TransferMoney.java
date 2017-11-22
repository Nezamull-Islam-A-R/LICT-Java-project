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

public class TransferMoney implements Initializable{

    @FXML
    private JFXTextField tReceiverAccNo;

    @FXML
    private JFXTextField tPass;

    @FXML
    private JFXTextField tamaount;

    @FXML
    private Label tTextShow;

    @FXML
    private JFXButton tTransferCluick;

    @FXML
    private JFXComboBox<String> tselctAccType;
    ObservableList<String> list = FXCollections.observableArrayList("Savings Account","Current Account");

    @FXML
    private JFXTextField tAccNo;
    
    private String rAcc,sPass,sAmount,sAcc,sAccType;
    
    @FXML
    void handleTransfer(ActionEvent event) {
    	int flagT = 0;
    	flagT = initVariables();
    	ConnMyDb myCon = new ConnMyDb();
    	int flagSQl = 0;
    	System.out.println(rAcc+" "+sAcc+" "+sPass+" "+sAccType+" "+sAccType+" Flag "+flagT);
    	if(flagT == 1){
    		flagSQl = myCon.transferMoneyHandleSql(rAcc,sPass,sAmount,sAcc,sAccType);
    		if(flagSQl == 1)
    			tTextShow.setText("Money has been sent successfully");
    		else
    			tTextShow.setText("Invalid Input, try again");
    	}else{
    		tTextShow.setText("Invalid Input, Try Again");
    	}
    }
    
    private int initVariables() {
		// TODO Auto-generated method stub
		int flagVar = 0;
		try{
			rAcc = tReceiverAccNo.getText().toString();
			sPass = tPass.getText().toString();
			sAmount = tamaount.getText().toString();
			sAcc = tAccNo.getText().toString();
			sAccType = tselctAccType.getValue().toString();
			flagVar = 1;
		}catch(Exception e){
			tTextShow.setText("Error Input or "+e.getMessage());
		}
		return(flagVar);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		tselctAccType.setItems(list);
	}
}
