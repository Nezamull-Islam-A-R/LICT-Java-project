package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.MySQLConnection;

public class ConnMyDb {
	//.....Sql
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/bankingsystem";
    static final String DB_USERNAME = "root";
    static final String DB_PASS = "";
    String sql1,sql2,sql3;
    String passFound = "";
    Connection myConn = null;
    
    
	public void payStuffs() {
		// TODO Auto-generated method stub
		sql1 = "SELECT `name`, `phn`, `nid`, `job` FROM `signup` ";
		int flagtotal = 0;
		try{
			try{
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf){
			    System.out.println("Driver could not be loaded: " + cnf);
			}
			Connection myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery(sql1);
			String st1,st2,st3,st4,st5;
			int adminSalary = 35000,acManagerSalary = 30000,cashierSalary = 25000,stuffHeadSalary =30000 ,infoDeskSalary = 23000;
			String accNo;
			while(myRs.next()){
				st1 = myRs.getString("nid");
				st2 = myRs.getString("job");
				accNo = findAcc(st1);
				if(accNo != null){
					int mon = 0;
					if(st2.compareTo("Admin") == 0)
						mon = adminSalary;
					else if(st2.compareTo("Account Manager") == 0)
						mon = acManagerSalary;
					else if(st2.compareTo("Cashier") == 0)
						mon = cashierSalary;
					else if(st2.compareTo("Stuff Head") == 0)
						mon = stuffHeadSalary;
					else if(st2.compareTo("Info Desk") == 0)
						mon = infoDeskSalary;
					
					System.out.println("accNo "+accNo+"st2: "+st2);
					
					Date date = java.util.Calendar.getInstance().getTime(); 
					if(accNo.length() > 2)
						sql1 = "INSERT INTO `Transc` VALUES ('"+accNo+"',0,"+mon+",'"+date+"','Payment')";
					
					executeSQL2(sql1);
					System.out.println(sql1);
				}else{
					System.out.println("Acc No is NULL.");
				}
			}
		}catch(Exception e){
			System.out.println("SQL SelectBalance Error. "+e.getMessage());
		}
	}
    
    private String findAcc(String st1) {
		// TODO Auto-generated method stub
    	sql1 = "SELECT `AccNo`, `Name` FROM `users_info` WHERE Nid="+st1;
		int flagtotal = 0;
		String stx ="" ,sty = "";
		try{
			try{
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf){
			    System.out.println("Driver could not be loaded: " + cnf);
			}
			Connection myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery(sql1);
			
			
			while(myRs.next()){
				stx = myRs.getString("AccNo");
				sty = myRs.getString("Name");				
				
			}
		}catch(Exception e){
			System.out.println("SQL SelectBalance Error. "+e.getMessage());
		}
		System.out.println("stx : "+stx);
		return stx;
	}
    
    private void executeSQL2(String sql) {
		// TODO Auto-generated method stub
    	try{
    		try{
			    // loads com.mysql.jdbc.Driver into memory
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf){
			    System.out.println("Driver could not be loaded: " + cnf);
			}
    		myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
			Statement myStmt = myConn.createStatement();
			
			myStmt.execute(sql);
			myConn.commit();
			
    	}catch(Exception e){
    		System.out.println("User SQL insert Error. "+e.getMessage());
			System.out.println(sql);			
    	}
	}

	public ArrayList<String> getAllStuffListItem() {
		ArrayList<String> list0=new ArrayList<String>(); 
		
		sql1 = "SELECT * FROM `signup`";
		try{
			list0 = executeSQLStuffList(sql1);
		}catch(Exception e){
			System.out.println(e.getMessage()+" ListView info");
		}
		
		return list0;
	}
    
    public ArrayList<String> getStuffListItem() {
		ArrayList<String> list0=new ArrayList<String>(); 
		
		sql1 = "SELECT * FROM `signup` WHERE Active_member=1";
		try{
			list0 = executeSQLStuffList(sql1);
		}catch(Exception e){
			System.out.println(e.getMessage()+" ListView info");
		}
		
		return list0;
	}
    
    private ArrayList<String> executeSQLStuffList(String sql22) {
		// TODO Auto-generated method stub
		ArrayList<String> list=new ArrayList<String>();
		//list.add("Stuff Name \t\t Job");
		int flagtotal = 0;
		try{
			try{
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf){
			    System.out.println("Driver could not be loaded: " + cnf);
			}
			Connection myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery(sql22);
			String st1,st2,st3,st4,st5;
			while(myRs.next()){
				st1 = myRs.getString("name");
				st2 = myRs.getString("job");
				//System.out.println(passFound);
				list.add("STUFF NAME: "+st1+"\t\t\tJOB: "+st2);
			}
		}catch(Exception e){
			System.out.println("SQL SelectBalance Error. "+e.getMessage());
			System.out.println(sql22);
		}
		return list;
	}
    
	public ArrayList<String> getDataListItem(String getId) {
		ArrayList<String> list0=new ArrayList<String>(); 
		
		sql1 = "SELECT * FROM `transc` WHERE uAccNo="+getId;
		try{
			list0 = executeSQLInfoList(sql1);
		}catch(Exception e){
			System.out.println(e.getMessage()+" ListView info");
		}
		
		return list0;
	}
	private ArrayList<String> executeSQLInfoList(String sql22) {
		// TODO Auto-generated method stub
		ArrayList<String> list=new ArrayList<String>();
		list.add("Acc No. \tWithdraw \tDeposit \t\tTime and Date \t\t\t\tReff. Acc.");
		int flagtotal = 0;
		try{
			try{
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf){
			    System.out.println("Driver could not be loaded: " + cnf);
			}
			Connection myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery(sql22);
			String st1,st2,st3,st4,st5;
			while(myRs.next()){
				st1 = myRs.getString("uAccNo");
				st2 = myRs.getString("uWithdraw");
				st3 = myRs.getString("uDeposit");
				st4 = myRs.getString("timeNdate");
				st5 = myRs.getString("reffAcc");
				//System.out.println(passFound);
				list.add(st1+"\t\t\t"+st2+"\t\t"+st3+"\t\t"+st4+"\t\t\t"+st5);
			}
		}catch(Exception e){
			System.out.println("SQL SelectBalance Error. "+e.getMessage());
			System.out.println(sql22);
		}
		return list;
	}

    
	public int transferMoneyHandleSql(String rAcc, String sPass, String sAmount, String sAcc, String sAccType) {
		// TODO Auto-generated method stub
		int flagForTrns = 0;
		int flagT = 0;
		flagForTrns = executeReturnSQL(sAcc,sPass,sAccType);
		if(flagForTrns == 1){
			int mon = Integer.valueOf(sAmount);
			Date date = java.util.Calendar.getInstance().getTime(); 
			sql1 = "INSERT INTO `Transc` VALUES ('"+sAcc+"',"+mon+",0,'"+date+"','No Ref.')";
			sql2 =  "INSERT INTO `Transc` VALUES ('"+rAcc+"',0,"+mon+",'"+date+"','"+sAcc+"')";
			executeSQL2(sql1,sql2);
			
			flagT = 1;
		}
		
		return flagT;
	}
	private void executeSQL2(String sql1,String sql2) {
		// TODO Auto-generated method stub
    	try{
    		try{
			    // loads com.mysql.jdbc.Driver into memory
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf){
			    System.out.println("Driver could not be loaded: " + cnf);
			}
    		myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
    		myConn.setAutoCommit(false);
			Statement myStmt = myConn.createStatement();
			
			myStmt.executeUpdate(sql1);	
			myStmt.executeUpdate(sql2);	
			myConn.commit();
			
    	}catch(SQLException se){
    		try{
    			myConn.rollback();
    			System.out.println("Rollbacking...");
    			System.out.println(sql1+"\n"+sql2);	
    		}catch(SQLException e){
    			e.printStackTrace();
    		}
    	}catch(Exception e){
    		System.out.println("User SQL insert Error. "+e.getMessage());
			System.out.println(sql1+"\n"+sql2);			
    	}
	}
	
	public int withDrawMoneyHandleSql(String accNo, String accPass, String amount, String seectType) {
		// TODO Auto-generated method stub
		int flagWd = 0,flagForWD = 0;
		flagForWD = executeReturnSQL(accNo,accPass,seectType);
		if(flagForWD == 1){
			int mon = Integer.valueOf(amount);
			Date date = java.util.Calendar.getInstance().getTime(); 
			sql2 = "INSERT INTO `Transc` VALUES ('"+accNo+"',"+mon+",0,'"+date+"','No Ref.')";
			executeSQL(sql2);
			flagForWD = 1;
		}
		return flagWd;
	}

	public int showUpBalance(String accId, String accPin, String accType) {
		// TODO Auto-generated method stub
		int flagForCBal = 0;
		int totalBalance = 0,totalBalD = 0,totalBalW = 0;
		flagForCBal = executeReturnSQL(accId,accPin,accType);
		if(flagForCBal == 1){
			//sql2 = "SELECT * FROM transc WHERE uAccNo ="+accId;
			sql2 = "SELECT SUM(uDeposit) AS total FROM transc WHERE uAccNo = "+accId;
			sql3 = "SELECT SUM(uWithdraw) AS total FROM transc WHERE uAccNo = "+accId;
			totalBalD = executeSQLselect(sql2);
			totalBalW = executeSQLselect(sql3);
			System.out.println(totalBalD+" - "+totalBalW);
			totalBalance = totalBalD - totalBalW;
		}
		return (totalBalance);
	}
    
	private int executeSQLselect(String sql22) {
		// TODO Auto-generated method stub		
		int flagtotal = 0;
		try{
			try{
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf){
			    System.out.println("Driver could not be loaded: " + cnf);
			}
			Connection myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery(sql22);
			while(myRs.next()){
				passFound = myRs.getString("total");
				//System.out.println(passFound);
				flagtotal = Integer.valueOf(passFound);
			}
		}catch(Exception e){
			System.out.println("SQL SelectBalance Error. "+e.getMessage());
			System.out.println(sql22);
		}
		return flagtotal;
	}

	public int depositMoneySQL(String accNo, String accPin, String moneyD, String accType) {
		// TODO Auto-generated method stub
		int flagForDep = 0;
		flagForDep = executeReturnSQL(accNo,accPin,accType);
		
		if(flagForDep == 1){
			int mon = Integer.valueOf(moneyD);
			Date date = java.util.Calendar.getInstance().getTime(); 
			sql2 = "INSERT INTO `Transc` VALUES ('"+accNo+"',0,"+mon+",'"+date+"','No Ref.')";
			executeSQL(sql2);
		}
		return flagForDep;
	}
    
    private int executeReturnSQL(String accNo, String accPin, String accType) {
		// TODO Auto-generated method stub
    	int flagDepositPin = 0;
    	int accNoChk = Integer.valueOf(accNo);
    	
    	sql1 = "SELECT * FROM `users_info` WHERE AccNo = "+accNoChk+" AND pass = '"+accPin+"' AND AcccType = '"+accType+"'";
		try{
			try{
			    // loads com.mysql.jdbc.Driver into memory
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf){
			    System.out.println("Driver could not be loaded: " + cnf);
			}
			Connection myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery(sql1);
			while(myRs.next()){
				passFound = myRs.getString("pass");
				flagDepositPin = 1;
			}
		}catch(Exception e){
			System.out.println("SQL Select Error. "+e.getMessage());
			System.out.println(sql1);
		}
    	
		return flagDepositPin;
	}

	private void executeSQL(String sql) {
		// TODO Auto-generated method stub
    	try{
    		try{
			    // loads com.mysql.jdbc.Driver into memory
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf){
			    System.out.println("Driver could not be loaded: " + cnf);
			}
    		myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
    		myConn.setAutoCommit(false);
			Statement myStmt = myConn.createStatement();
			
			myStmt.executeUpdate(sql);			
			myConn.commit();
			
    	}catch(SQLException se){
    		try{
    			myConn.rollback();
    			System.out.println("Rollbacked");
    		}catch(SQLException e){
    			e.printStackTrace();
    		}
    	}catch(Exception e){
    		System.out.println("User SQL insert Error. "+e.getMessage());
			System.out.println(sql);			
    	}
	}

	public int insertUsersInfo(int accNo,String openName, String openNid, String openHome, String openPresent, String openPhn,
			String openEdu, String openFather, String openMother, String openNote, String openAccType){
    	//String accNoSet = String.valueOf(accNo);
    	int oFlag = 0;
    	oFlag = checkLastAcc();
    	oFlag += 1;
    	Date date=java.util.Calendar.getInstance().getTime();  
    	System.out.println(date);
    	sql1 = "INSERT INTO `users_info` VALUES ('"+oFlag+"','"+openName+"','"+openNid+"','"+openHome+"','"+openPresent+"','"+openPhn+"','"+openEdu+"','"+openFather+"','"+openMother+"','"+openNote+"','"+openAccType+"',1)";
    	sql2 = "INSERT INTO `Transc` VALUES ('"+oFlag+"',0,500,'"+date+"','No Ref.')";
    	try{
    		try{
			    // loads com.mysql.jdbc.Driver into memory
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf){
			    System.out.println("Driver could not be loaded: " + cnf);
			}
    		myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
    		myConn.setAutoCommit(false);
			Statement myStmt = myConn.createStatement();
			
			myStmt.executeUpdate(sql1);
			myStmt.executeUpdate(sql2);
			
			myConn.commit();
    	}catch(SQLException se){
    		try{
    			myConn.rollback();
    			System.out.println("Rollbacking...");
    		}catch(SQLException e){
    			e.printStackTrace();
    		}
    	}catch(Exception e){
    		System.out.println("User SQL insert Error. "+e.getMessage());
			System.out.println(sql1);			
    	}
    	return (oFlag);
    }
    
    private int checkLastAcc() {
		// TODO Auto-generated method stub
    	String sql = "SELECT * FROM `users_info` ";
    	String accId = "";
    	int max = 0;
		int temp = 0;
    	try{
			try{
			    // loads com.mysql.jdbc.Driver into memory
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf){
			    System.out.println("Driver could not be loaded: " + cnf);
			}
			Connection myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery(sql);
			
			while(myRs.next()){
				accId = myRs.getString("AccNo");
				temp = Integer.valueOf(accId);
				if(temp> max)
					max = temp;
				//System.out.println("Max: "+max);
			}
		}catch(Exception e){
			System.out.println(accId+" SQL Select MAX(NID) Error. "+e.getMessage());
			System.out.println(sql);
		}
    	
		return max;
	}

	public String findPassSqlDatabase(String mail,String phn,String nid) {
		// TODO Auto-generated method stub
    	sql1 = "SELECT  * FROM `signup` WHERE e_mail='"+mail+"' and phn='"+phn+"' and pass='"+nid+"'";
		try{
			try{
			    // loads com.mysql.jdbc.Driver into memory
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf){
			    System.out.println("Driver could not be loaded: " + cnf);
			}
			Connection myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery(sql1);
			while(myRs.next()){
				passFound = myRs.getString("pass");
			}
		}catch(Exception e){
			System.out.println("SQL Select Error. "+e.getMessage());
			System.out.println(sql1);
		}
		return (passFound);
	}

	public int closeUsersAcc(String closeAccNo, String closeAccType, String closeAccPin) {
		// TODO Auto-generated method stub
		int flagClose = 0;
		String sql = "UPDATE `users_info` SET `Activity`=0 WHERE AccNo = '"+closeAccNo+"' and AcccType = '"+closeAccType+"'and pass = '"+closeAccPin+"'";
		try{
    		try{
			    // loads com.mysql.jdbc.Driver into memory
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf){
			    System.out.println("Driver could not be loaded: " + cnf);
			}
    		Connection myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
			Statement myStmt = myConn.createStatement();
			myStmt.executeUpdate(sql);
			flagClose = 1;
    	}catch(Exception e){
    		System.out.println("User Close Acc SQL insert Error. "+e.getMessage());
			System.out.println(sql);			
    	}
		return flagClose;
	}

	public long getDepositTotal(int depoFlag) {
		long moneyShow = 0;
		try{
			try{
			    // loads com.mysql.jdbc.Driver into memory
			    Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException cnf){
			    System.out.println("Driver could not be loaded: " + cnf);
			}
			Connection myConn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
			Statement myStmt = myConn.createStatement();
			if(depoFlag == 10){
				sql1 = "SELECT SUM(uDeposit) AS total FROM transc";
			}else if(depoFlag == 20){
				sql1 = "SELECT SUM(uWithdraw) AS total FROM transc";
			}else{
				sql1 = "SELECT SUM(uDeposit) AS total FROM transc WHERE NOT reffAcc = 'No Ref.' AND NOT reffAcc = 'Payment' AND NOT reffAcc = 'No Ref'";
			}
			ResultSet myRs = myStmt.executeQuery(sql1);
			while(myRs.next()){
				String outGet = myRs.getString("total");
				//System.out.println(passFound);
				moneyShow = Integer.valueOf(outGet);
			}
		}catch(Exception e){
			System.out.println("SQL Select Error. "+e.getMessage());
		}
		return moneyShow;
	}

	public int logOut(String mySt, String myMail, String myPass) {
		// TODO Auto-generated method stub
		int myFlagLog = 0;
		//UPDATE `signup` SET `Active_member`= 0 WHERE e_mail = "anamul@gmail.com" AND pass = "123074"
		sql1 = "UPDATE `signup` SET `Active_member`= 0 WHERE e_mail = '"+myMail+"' AND pass = '"+myPass+"'";
		try{
			executeSQL2(sql1);
			myFlagLog = 1;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return(myFlagLog);
	}


}
