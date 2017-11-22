package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class PresentStuff implements Initializable{

    @FXML
    private ListView<String> stuffList;
    ArrayList<String> list;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    		ConnMyDb myCon = new ConnMyDb();
        	try{
        		list = myCon.getStuffListItem();
        	}catch(Exception e){
        		System.out.println("sql list Error.");
        	}
        	stuffList.getItems().addAll(list);
	}

}
