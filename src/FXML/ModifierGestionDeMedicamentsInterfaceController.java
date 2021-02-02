package FXML;

import MainApp.PhManager;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;


public class ModifierGestionDeMedicamentsInterfaceController {

    @FXML
    private TextField nomf;
    
    @FXML
    private TextField nomm;
    
    @FXML
    private TextField qtem;

    @FXML
    private TextField prixm;
    
    @FXML
    void InsererOnAction(ActionEvent event) {
        if ( !qtem.getText().isEmpty() && !prixm.getText().isEmpty() && !nomf.getText().isEmpty()) {
	    try {
	    	String sql =  "UPDATE `gestiondemedicaments` SET `nomfm`= '"+nomf.getText()+"', `qtem`= '"+qtem.getText()+"', `prixm`= '"+prixm.getText()+"' WHERE `nommed` = '"+nomm.getText()+"'" ;
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
        nomf.setText(GestionDeMedicamentsController.selectedname);
    }
    
}