package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InfoJobsController {

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
    private TextField searchId;

    @FXML
    private ListView<String> listView;

    @FXML
    private JFXButton Search;
    String getId ;
    ArrayList<String> list;
    @FXML
    void TransferMoneyHandle(ActionEvent event) {
//    	try {
//			Parent root = FXMLLoader.load(getClass().getResource("/uifiles/TransferMoney.fxml"));
//			Scene scene = new Scene(root);			
//			Stage tfStage = new Stage();
//			tfStage.setScene(scene);
//			tfStage.show();
//		} catch(Exception e) {
//			System.out.println("TransferMoney page problem.");
//			e.printStackTrace();
//		}
    	notifyMe.setText("You can't Access, This section is for Cashier section.");
    }

    @FXML
    void depositHandle(ActionEvent event) {
//    	try {
//			Parent root = FXMLLoader.load(getClass().getResource("/uifiles/Deposit.fxml"));
//			Scene scene = new Scene(root);			
//			Stage dpStage = new Stage();
//			dpStage.setScene(scene);
//			dpStage.show();
//		} catch(Exception e) {
//			System.out.println("Deposit page problem.");
//			e.printStackTrace();
//		}
    	notifyMe.setText("You can't Access, This section is for Cashier section.");
    }

    @FXML
    void withdrawHandle(ActionEvent event) {
//    	try {
//			Parent root = FXMLLoader.load(getClass().getResource("/uifiles/WithdrawCash.fxml"));
//			Scene scene = new Scene(root);			
//			Stage wcStage = new Stage();
//			wcStage.setScene(scene);
//			wcStage.show();
//		} catch(Exception e) {
//			System.out.println("WithdrawCash page problem.");
//			e.printStackTrace();
//		}
    	notifyMe.setText("You can't Access, This section is for Cashier section.");
    }
    
    @FXML
    void checkUserBalanceHandle(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/uifiles/CheckUsersBalanace.fxml"));
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
    void actionSearch(ActionEvent event) {
//    	String st[] = {"ban","vab","kom"};
//    	ArrayList<String> list=new ArrayList<String>(); 
//    	  list.add("Ravi");
//    	  list.add("Vijay");  
//    	  list.add("Ravi");  
//    	  list.add("Ajay");
//    	listView.getItems().addAll(list);
    	int flagGetIn = initVar();
    	if(flagGetIn == 1){
    		ConnMyDb myCon = new ConnMyDb();
        	int flagSQl = 0;
        	try{
        		list = myCon.getDataListItem(getId);
        	}catch(Exception e){
        		System.out.println("sql list Error.");
        	}
        	listView.getItems().addAll(list);
    	}
    }
    
	private int initVar() {
		// TODO Auto-generated method stub
		int flagLocal = 0;
		try{
			flagLocal = 1;
			getId = searchId.getText().toString();			
		}catch(Exception e){
			notifyMe.setText("Input Error, Try again.");
		}
		return flagLocal;
	}

}
