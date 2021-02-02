package FXML;

import MainApp.PhManager;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;


public class ModifierGestionDeFournisseursInterfaceController  {

    @FXML
    private TextField id;

    @FXML
    private TextField nomf;
    
    @FXML
    private TextField tel;

    @FXML
    private TextField ville;
    
    @FXML
    private TextField adresse;
    
    
    @FXML
    void InsererOnAction(ActionEvent event) {
        if ( !nomf.getText().isEmpty() && !tel.getText().isEmpty() && !adresse.getText().isEmpty() && !ville.getText().isEmpty()) {
	    try {
	    	String sql =  "UPDATE `gestiondefournisseurs` SET `nomfourn`= '"+nomf.getText()+"', `adresse`= '"+adresse.getText()+"', `numtel`= '"+tel.getText()+"', `ville`= '"+ville.getText()+"' WHERE `idf` = '"+id.getText()+"'" ;
	    	Statement smt = PhManager.con.createStatement() ;
	        smt.executeUpdate((sql)) ;
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Succès");
		alert.setHeaderText(null);
		alert.setContentText("Modification terminée");
		alert.showAndWait();
	        }catch(Exception e) {
	    	    System.out.println(e);
	    	    }
	    }else {
	    	Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Un de vos champs est vide");
	    	alert.setContentText("Veuillez remplir tous les champs!");
                alert.showAndWait();

	    }
    }
    public void initialize() {
        id.setText(GestionDeFournisseursController.selectedname);
    }
    
}