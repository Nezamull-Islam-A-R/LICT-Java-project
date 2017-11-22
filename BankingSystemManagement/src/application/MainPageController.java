package application;
//import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainPageController implements Initializable{
	
    @FXML
    private JFXButton privacy;

    @FXML
    private JFXButton terms;

    @FXML
    private JFXButton copyR;
    
    
	@FXML
    private JFXTextField eName;
	private String uName;

    @FXML
    private JFXTextField ePhn;
    private String uPhn;
    
    @FXML
    private JFXTextField eNID;
    private String uNID;
    
    @FXML
    private JFXTextField eHomeAddress;
    private String uHomeAdd;
    
    @FXML
    private JFXTextField eFavAnimal;
    private String uFavAnimal;
    
    @FXML
    private JFXTextField eEmail;
    private String uEmail;
    
    @FXML
    private JFXTextField ePassWord;
    private String uPass;
    
    @FXML
    private JFXTextField eConfPass;
    private String uConfPass;
    
    @FXML
    private JFXButton eSubmit;
    
    private String uJob;
    private int uPhnInt;
    private int uNidInt;
    private int activeMemberDef = 1;
    private String signInJob;
    private String warningMsg = "WARNING: Wrong Input";
    
    @FXML
    private Text signiWarning;
    //.....Sql
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/bankingsystem";
    static final String DB_USERNAME = "root";
    static final String DB_PASS = "";
    String sql1;
	//.....interface

    @FXML
    private JFXButton signUpButton;
    
    @FXML
    private TextField enterMailiD;

    @FXML
    private TextField enterPass;
    @FXML
    private Text show;
    
    String mailId,passWord;
    @FXML
    public JFXComboBox<String> selectJob;
    
    ObservableList<String> list = FXCollections.observableArrayList("Admin","Account Manager","Info Desk","Stuff Head","Cashier");
 

    @FXML
    void handleSignUpButton(ActionEvent event) {
    	try {    		
			Parent root = FXMLLoader.load(getClass().getResource("/application/SignUpController.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			System.out.println("sign up page problem.");
			e.printStackTrace();
		}
    }
    
    @FXML
    private JFXButton forgotButton;

    @FXML
    void findForgot(ActionEvent event) {
    	try{
    		Parent root = FXMLLoader.load(getClass().getResource("/application/Forgot_Pass.fxml"));
    		Scene sceneForgot = new Scene(root);
    		Stage forGotStage = new Stage();
    		forGotStage.setScene(sceneForgot);
    		forGotStage.show();
    		
    		
    	}catch(Exception e){
    		System.out.println("Forgot Page Problem");
    		e.printStackTrace();
    	}
    }
    
    @FXML
    private JFXButton signInButton;
   
    @FXML
    void onSignInAction(ActionEvent event) {
    	int flagSingInput = 0;
    	int flag = 0;
    	try{
    		try{
    			mailId = enterMailiD.getText().toString(); 
        		passWord = enterPass.getText().toString();
        		System.out.println(mailId+' '+passWord);
        		signInJob = selectJob.getValue();
    		}catch(Exception e){
    			System.out.println("Sign in Error Input.");
    		}    		
    		GlobalVar glob = new GlobalVar();
    		//----Default
//    		mailId = "Tiger@gmail.com";
//    		passWord = "01923";
//    		signInJob = "Admin";
    		glob.bankJob = signInJob;
    		glob.gMail = mailId;
    		glob.gPass = passWord;
    		
    		flagSingInput = checkSignInput(mailId,passWord,signInJob);
    		
    		if(flagSingInput != 0){
	    		sql1 = "SELECT  * FROM `signup` WHERE e_mail='"+mailId+"' and job='"+signInJob+"' and pass='"+passWord+"'";
	    		//System.out.println(sql1);
	    		flag  = signInMethodDB(sql1);
	    		if(flag != 0 ){
		    		Parent root = FXMLLoader.load(getClass().getResource("/application/ChooseOpt.fxml"));
		    		Scene logInScn = new Scene(root);
		    		Stage logInStage = new Stage();
		    		glob.loginStage = logInStage;
		    		logInStage.setScene(logInScn);
		    		logInStage.show();
		    		//logInStage.close();
		    		signiWarning.setText("You have logged in Succesfully");
		    		sql1 = "UPDATE `signup` SET `Active_member`="+activeMemberDef+" WHERE e_mail = '"+mailId+"'";
		    		updateActiveMembers(sql1);
	    		}else{
	    			signiWarning.setText(warningMsg);
	    		}
    		}else{
    			signiWarning.setText("Input Invalid");
    		}
    		
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		System.out.println("logIn problem");
    	}
    }

	private void updateActiveMembers(String sql12) {
		// TODO Auto-generated method stub
		try{
			try
			{
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf) 
			{
			    System.out.println("Driver could not be loaded: " + cnf);
			}
			Connection myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
			Statement myStmt = myConn.createStatement();
			
			myStmt.executeUpdate(sql12);
		}catch(Exception e){
			System.out.println("SQL Update Error. "+e.getMessage());
			System.out.println(sql12);
		}
	}

	private int checkSignInput(String mailId2, String passWord2, String signInJob2) {
		int lop = 1;
		if((mailId2.length() < 10) || (passWord2.length()<4) || (signInJob2.length() == 0))
			lop = 0;
		return lop;
	}

	private int signInMethodDB(String sql12) {
		// TODO Auto-generated method stub
		int flag = 0;
		try{
			try
			{
			    // loads com.mysql.jdbc.Driver into memory
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf) 
			{
			    System.out.println("Driver could not be loaded: " + cnf);
			}
			Connection myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);//,DB_USERNAME,DB_PASS
			Statement myStmt = myConn.createStatement();
			
			//myStmt.executeUpdate(sql12);
			ResultSet myRs = myStmt.executeQuery(sql12);
			//System.out.println(myRs);
			
			while(myRs.next()){
				System.out.println(myRs.getString("name"));
				flag += 1;
			}
		}catch(Exception e){
			System.out.println("SQL insert Error. "+e.getMessage());
			System.out.println(sql12);
		}		
		return (flag);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		selectJob.setItems(list);
	}
	
	public void jobSelection(ActionEvent event){
		System.out.println(selectJob.getValue());
	}
	
	//SignUp button Action
    @FXML
    void eSubmitHandle(ActionEvent event) {
    	getUsersInfo();
    	System.out.println(uName+'\n'+uPhn+'\n'+uNID+'\n'
    			+uJob+'\n'+uHomeAdd+'\n'+uFavAnimal+'\n'
    			+uEmail+'\n'+uPass+'\n'+uConfPass);
    	sql1 = "INSERT INTO `signUp` VALUES ('"+uName+"','"+uPhn+"','"+uNID+"','"+uJob+"','"+uHomeAdd+"','"+uFavAnimal+"','"+uEmail+"','"+uPass+"',"+activeMemberDef+")";
    	//Enter Data in mySql Database
    	intoSqlDatabase();

    }
    
	private void getUsersInfo() {
		// TODO Auto-generated method stub
		try{
			uName = eName.getText().toString();
	    	uPhn = ePhn.getText().toString();
	    	uNID = eNID.getText().toString();
	    	uJob = selectJob.getValue().toString();
	    	uHomeAdd = eHomeAddress.getText().toString();
	    	uFavAnimal = eFavAnimal.getText().toString();
	    	uEmail = eEmail.getText().toString();
	    	uPass = ePassWord.getText().toString();
	    	uConfPass = eConfPass.getText().toString();
		}catch(Exception e){
			System.out.println("User get Input Error");
		}
    	
    	//.......default test
//    	uName = "Asif Abdullah";
//    	uPhn = "0193201882";
//    	//uPhnInt = Integer.valueOf(uPhn);
//    	uNID = "1223109039";
//    	//uNidInt = Integer.valueOf(uNID);
//    	uJob = "Admin";
//    	uHomeAdd = "myHome is Bangladesh";
//    	uFavAnimal = "Tiger";
//    	uEmail = "Tiger@gmail.com";
//    	uPass = "01923";
//    	uConfPass = "01923";
//    	
//    	
	}
	
	private void intoSqlDatabase() {
		// TODO Auto-generated method stub
		try{
			try
			{
			    // loads com.mysql.jdbc.Driver into memory
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf) 
			{
			    System.out.println("Driver could not be loaded: " + cnf);
			}
			Connection myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);//,DB_USERNAME,DB_PASS
			Statement myStmt = myConn.createStatement();
			
			myStmt.executeUpdate(sql1);
			//ResultSet myRs = myStmt.executeQuery(sql1);
//			while(myRs.next()){
//				System.out.println(myRs.getString("name"));
//			}
		}catch(Exception e){
			System.out.println("SQL insert Error. "+e.getMessage());
			System.out.println(sql1);
		}
	}
	
    @FXML
    void handlePrivacy(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Privacy_Policy.fxml"));
			Scene scene = new Scene(root);			
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			System.out.println("Privacy problem.");
			e.printStackTrace();
		}
    }
    @FXML
    void copyHandle(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Copyright.fxml"));
			Scene scene = new Scene(root);			
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			System.out.println("Copyright problem.");
			e.printStackTrace();
		}
    }
    
    @FXML
    void handleTerms(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Terms.fxml"));
			Scene scene = new Scene(root);			
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			System.out.println("Terms problem.");
			e.printStackTrace();
		}
    }
    
}