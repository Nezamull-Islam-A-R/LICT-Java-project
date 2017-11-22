package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ForgotPassFinder implements Initializable{
	
    @FXML
    private JFXTextField fMailId;

    @FXML
    private JFXTextField fPhnNo;

    @FXML
    private JFXTextField fNidNo;

    @FXML
    private Label showPass;

    @FXML
    private JFXButton Check;
    
    //-----Get Value
    private String mailId;
    private String phnNo;
    private String nidNo;
    private String passGet = ""; 
    
    ConnMyDb conn = new ConnMyDb();
    @FXML
    void cheackPasswor(ActionEvent event) {
    	int flagForgot = getSetUsersInfor();
    	if(flagForgot != 0){
    		passGet = conn.findPassSqlDatabase(mailId, phnNo, nidNo);
    		showPass.setText("Your Password: "+passGet);
    	}else{
    		showPass.setText("Invalid input");
    	}
    }

	private int getSetUsersInfor() {
		// TODO Auto-generated method stub
		int retGet = 1;
		try{
			mailId = fMailId.getText().toString();
			phnNo = fPhnNo.getText().toString();
			nidNo = fNidNo.getText().toString();
		}catch(Exception e){
			System.out.println("ForGot pass Error . "+e.getMessage());
		}
		if(mailId.length()< 10 || phnNo.length() == 0 || nidNo.length() == 0)
			retGet = 0;
		return retGet;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
