package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CashierController implements Initializable{

    @FXML
    private JFXButton depositID;

    @FXML
    private JFXButton withdrawID;

    @FXML
    private JFXButton transferMoney;
    
    @FXML
    private JFXButton checkUsersBal;
    
    @FXML
    private Text notifyMe;

    @FXML
    void TransferMoneyHandle(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/uifiles/TransferMoney.fxml"));
			Scene scene = new Scene(root);			
			Stage tfStage = new Stage();
			tfStage.setScene(scene);
			tfStage.show();
			notifyMe.setText("");
		} catch(Exception e) {
			System.out.println("TransferMoney page problem.");
			e.printStackTrace();
		}
    }

    @FXML
    void depositHandle(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/uifiles/Deposit.fxml"));
			Scene scene = new Scene(root);			
			Stage dpStage = new Stage();
			dpStage.setScene(scene);
			dpStage.show();
			notifyMe.setText("");
		} catch(Exception e) {
			System.out.println("Deposit page problem.");
			e.printStackTrace();
		}
    }

    @FXML
    void withdrawHandle(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/uifiles/WithdrawCash.fxml"));
			Scene scene = new Scene(root);			
			Stage wcStage = new Stage();
			wcStage.setScene(scene);
			wcStage.show();
			notifyMe.setText("");
		} catch(Exception e) {
			System.out.println("WithdrawCash page problem.");
			e.printStackTrace();
		}
    }
    
    @FXML
    void checkUserBalanceHandle(ActionEvent event) {
//    	try {
//			Parent root = FXMLLoader.load(getClass().getResource("/uifiles/CheckUsersBalanace.fxml"));
//			Scene scene = new Scene(root);			
//			Stage wcStage = new Stage();
//			wcStage.setScene(scene);
//			wcStage.show();
//		} catch(Exception e) {
//			System.out.println("WithdrawCash page problem.");
//			e.printStackTrace();
//		}
    	notifyMe.setText("You can't Access, This section is for Info Desk section.");
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	

    @FXML
    void searchActionNothing(ActionEvent event) {
    	notifyMe.setText("You can't Access, This section is for Info Desk section.");
    }

}
