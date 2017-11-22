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
import javafx.scene.text.Text;

public class AccManagerController implements Initializable{
	@FXML
    private JFXTextField selectAccNo;

    @FXML
    private JFXTextField selectAcPin;

    @FXML
    private JFXButton clossAcc;

    @FXML
    private JFXComboBox<String> selectAccType;
    ObservableList<String> list = FXCollections.observableArrayList("Savings Account","Current Account");
    
    @FXML
    private Text closeTextInfo;

    @FXML
    private JFXComboBox<String> selectOpenAccType;

    @FXML
    private JFXTextField uName;

    @FXML
    private JFXTextField uNID;

    @FXML
    private JFXTextField uHomeAdd;

    @FXML
    private JFXTextField uPresentAdd;

    @FXML
    private JFXTextField uPhn;

    @FXML
    private JFXTextField uFathers;

    @FXML
    private JFXTextField uEdu;

    @FXML
    private JFXTextField uMothers;

    @FXML
    private JFXButton openAc;

    @FXML
    private JFXTextField uNote;

    @FXML
    private Text openTextInfo1;
    //------variables
    int accNo = 1001;
    String closeAccType,closeAccNo,closeAccPin;
    String openName,openNid,openHome,openPresent,openPhn,openEdu,
    openFather,openMother,openNote,openAccType;
    //-----call ConnMyDB
    ConnMyDb connDb = new ConnMyDb();
    
    
    @FXML
    void closeSelectAccType(ActionEvent event) {
    	System.out.println("Close ACC: "+selectAccType.getValue());    	
    }
    @FXML
    void openSelectAccType(ActionEvent event) {
    	System.out.println("Open ACC: "+selectOpenAccType.getValue());
    }

    @FXML
    void handleClossAcc(ActionEvent event) {
    	getCloseVariableinit();
    	int flagClose = connDb.closeUsersAcc(closeAccNo,closeAccType,closeAccPin);  
    	if(flagClose != 0){    		
    		closeTextInfo.setText("Account has been closed successfully.");
    	}else{
    		closeTextInfo.setText("Error input or Invalid User ");
    	}
    }

	@FXML
    void handleOpenAcc(ActionEvent event) {
    	getOpenVariableinit();
    	int flagOpen = connDb.insertUsersInfo(accNo,openName,openNid,openHome,openPresent,openPhn,openEdu,openFather,openMother,openNote,openAccType);
    	if(flagOpen != 0){
    		openTextInfo1.setText("Your Acc. No. is : "+flagOpen);
    		//accNo += 1;
    	}else{
    		openTextInfo1.setText("Some intput may wrong or duplicate entry");
    	}
    }

	private void getOpenVariableinit() {
		try{
			openName = uName.getText().toString();
			openNid = uNID.getText().toString();
			openHome = uHomeAdd.getText().toString();
			openPresent = uPresentAdd.getText().toString();
			openPhn = uPhn.getText().toString();
			openEdu = uEdu.getText().toString();
			openFather = uFathers.getText().toString();
			openMother = uMothers.getText().toString();
			openNote = uNote.getText().toString();
			openAccType = selectOpenAccType.getValue();
		}catch(Exception e){
			System.out.println("Open Acc problem "+e.getMessage());
			openTextInfo1.setText("Invalid input or Duplicate , Try again.");
		}
	}
    private void getCloseVariableinit() {
		// TODO Auto-generated method stub
		try{
			closeAccNo = selectAccNo.getText().toString();
			closeAccType = selectAccType.getValue();
			closeAccPin = selectAcPin.getText().toString();
		}catch(Exception e){
			System.out.println("Close Acc problem "+e.getMessage());
			openTextInfo1.setText("Invalid input or Duplicate, Try again.");
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		selectAccType.setItems(list);
		selectOpenAccType.setItems(list);
	}



}
