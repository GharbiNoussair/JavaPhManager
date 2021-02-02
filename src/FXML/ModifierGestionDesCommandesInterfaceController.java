package FXML;

import MainApp.PhManager;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;


public class ModifierGestionDesCommandesInterfaceController  {

    @FXML
    private TextField cmd;

    @FXML
    private TextField date;
    
    @FXML
    private TextField mnt;

    @FXML
    private TextField val;
    
    @FXML
    private TextField fournass;
    
    
    @FXML
    void InsererOnAction(ActionEvent event) {
        if ( !date.getText().isEmpty() && !mnt.getText().isEmpty() && !val.getText().isEmpty() && !fournass.getText().isEmpty()) {
	    try {
	    	String sql =  "UPDATE `gestiondecommandes` SET `datec`= '"+date.getText()+"', `montantc`= '"+mnt.getText()+"', `fournass`= '"+fournass.getText()+"', `validite`= '"+val.getText()+"' WHERE `numc` = '"+cmd.getText()+"'" ;
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
        cmd.setText(GestionDesCommandesController.selectedname);
    }
    
}