package FXML;

import MainApp.PhManager;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class ModifierGestionDeStockInterfaceController {

    @FXML
    private TextField mnomf;

    @FXML
    private TextField mqte;
    
    @FXML
    void InsererOnAction(ActionEvent event) {
        if ( !mqte.getText().isEmpty()) {
	    try {
	    	String sql =  "UPDATE `gestiondestock` SET `qtestock`= '"+mqte.getText()+"' WHERE `nomf` = '"+mnomf.getText()+"'" ;
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
        mnomf.setText(GestionDeStockController.selectedname);
    }
    
}