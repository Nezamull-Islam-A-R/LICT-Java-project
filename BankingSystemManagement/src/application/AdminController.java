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
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminController implements Initializable{
	

    @FXML
    private Text dateShow;
    
    @FXML
    private JFXButton presentStuff;
    

    @FXML
    private JFXButton payAll;


    @FXML
    private JFXButton tDepo;

    @FXML
    private JFXButton tWid;

    @FXML
    private Label amaountShow;

    @FXML
    private JFXButton tTransfer;

    @FXML
    private Label totalTransferShow;

    @FXML
    void onActionPresentStuffSee(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/uifiles/Present_Stuff_List.fxml"));
			Scene scene = new Scene(root);			
			Stage pStStage = new Stage();
			pStStage.setScene(scene);
			pStStage.show();
		} catch(Exception e) {
			System.out.println("Admin Present Stuff See page problem.");
			e.printStackTrace();
		}
    }	
    
    @FXML
    void payAllStuff(ActionEvent event) {
    	ConnMyDb myCon = new ConnMyDb();
    	try{
    		myCon.payStuffs();
    	}catch(Exception e){
    		System.out.println("sql list Error.");
    	}
    }

    @FXML
    void totalDepositAction(ActionEvent event) {
    	long totalDepoShow = 0;
    	int depoFlag = 10;
    	ConnMyDb conn = new ConnMyDb();
    	totalDepoShow = conn.getDepositTotal(depoFlag);
    	amaountShow.setText(totalDepoShow+" tk.");
    }

    @FXML
    void totalTransferHandle(ActionEvent event) {
    	long totalDepoShow = 0;
    	int depoFlag = 30;
    	ConnMyDb conn = new ConnMyDb();
    	totalDepoShow = conn.getDepositTotal(depoFlag);
    	totalTransferShow.setText(totalDepoShow+" tk.");
    }

    @FXML
    void totalWidrawHandle(ActionEvent event) {
    	long totalWithdrawShow = 0;
    	int depoFlag = 20;
    	ConnMyDb conn = new ConnMyDb();
    	totalWithdrawShow = conn.getDepositTotal(depoFlag);
    	amaountShow.setText(totalWithdrawShow+" tk.");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		java.util.Date date=new java.util.Date();  
		dateShow.setText(""+date);
	}

}
