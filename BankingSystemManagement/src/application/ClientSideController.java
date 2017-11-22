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

public class ClientSideController implements Initializable{
	@FXML
    private JFXButton cashierId;
	@FXML
	private Text chooseWarning;
	 
	GlobalVar myGlob = new GlobalVar(); 
	private String mySt = myGlob.bankJob;
	private String myMail = myGlob.gMail;
	private String myPass = myGlob.gPass;
    @FXML
    void cashierHandle(ActionEvent event) {
    	if(mySt == "Cashier" || mySt == "Admin"){
    	try {
    		//System.out.println("job Position : "+myGlob.bankJob);
			Parent root = FXMLLoader.load(getClass().getResource("/uifiles/CashierJobs.fxml"));
			Scene scene = new Scene(root);			
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			System.out.println("Cashier Jobs page problem.");
			e.printStackTrace();
		}
    	}else{
    		chooseWarning.setText("You can't access this");
    	}
    }
    
    
    @FXML
    private JFXButton infoButton;
    
    @FXML
    void infoDeskHandle(ActionEvent event) {
    	if(mySt == "Info Desk" || mySt == "Admin"){
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/uifiles/InfoJobs.fxml"));
			Scene scene = new Scene(root);			
			Stage ifStage = new Stage();
			ifStage.setScene(scene);
			ifStage.show();
		} catch(Exception e) {
			System.out.println("Info page problem.");
			e.printStackTrace();
		}
    	}else{
    		chooseWarning.setText("You can't access this");
    	}
    }
    
    @FXML
    private JFXButton accountManageID;
    
    @FXML
    void accountManagerHandle(ActionEvent event) {
    	if(mySt == "Account Manager" || mySt == "Admin"){
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/uifiles/ManagerJob.fxml"));
			Scene scene = new Scene(root);			
			Stage managerStage = new Stage();
			managerStage.setScene(scene);
			managerStage.show();
		} catch(Exception e) {
			System.out.println("Cashier Jobs page problem.");
			e.printStackTrace();
		}
    	}else{
    		chooseWarning.setText("You can't access this");
    	}
    }
    
    @FXML
    private JFXButton adminButton;
    @FXML
    void onAdminAction(ActionEvent event) {
    	if(mySt == "Admin" || mySt == "Admin"){
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/uifiles/Admin_sec.fxml"));
			Scene scene = new Scene(root);			
			Stage managerStage = new Stage();
			managerStage.setScene(scene);
			managerStage.show();
		} catch(Exception e) {
			System.out.println("Admin page problem.");
			e.printStackTrace();
		}
    	}else{
    		chooseWarning.setText("You can't access this");
    	}
    }
    
    @FXML
    void stuffHeadActionHandle(ActionEvent event) {
    	if(mySt == "Stuff Head" || mySt == "Admin"){
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/uifiles/All_Stuff.fxml"));
			Scene scene = new Scene(root);			
			Stage managerStage = new Stage();
			managerStage.setScene(scene);
			managerStage.show();
		} catch(Exception e) {
			System.out.println("Admin page problem.");
			e.printStackTrace();
		}
    	}else{
    		chooseWarning.setText("You can't access this");
    	}
    }
    @FXML
    void handleLogOut(ActionEvent event) {
    	try{
    		ConnMyDb conn = new ConnMyDb();
    		int flagLog = conn.logOut(mySt,myMail,myPass);
    		if(flagLog == 1){
    			myGlob.loginStage.close();
    			System.out.println("Logged out successfully");
    		}
    		else{
    			System.out.println("Something is wrong");
    		}
    	}catch(Exception e){
    	System.out.println(e.getMessage());
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}	
}
