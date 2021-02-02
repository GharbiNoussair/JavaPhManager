package FXML;

import MainApp.PhManager;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

  
    @FXML
    private TextField login;

    @FXML
    private PasswordField pswd;

    
    @FXML
    void loginActionAdm(ActionEvent event) {

    	Parent root;
        
        	try {
        	String sql =  "select * from login where login = '"+login.getText()+"' and mdp = '"+ pswd.getText()+"'" ;
	        Statement smt = PhManager.con.createStatement() ;
	        ResultSet rs = smt.executeQuery(sql) ;
	        int i = 0;
	        while (rs.next()) {
	        	i++;	 
	        }
	         if (i==1) {
	        	
		       try{
		            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("HomeInterface.fxml"));
                            root = loader.load();
                            Scene scene = new Scene(root);        
                            ((Stage)((Node)event.getTarget()).getScene().getWindow()).setScene(scene);
		            }
		            catch (IOException e) {
		                e.printStackTrace();
		            }
	         }else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez vérifier votre Identifiant et votre Mot de passe !");
                    alert.showAndWait();
	         }
        }catch(Exception e) {
        	System.out.println(e);
        }
    }
}