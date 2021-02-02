package FXML;

import MainApp.PhManager;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;


public class ModifierGestionDeVenteInterfaceController  {

    @FXML
    private TextField id;

    @FXML
    private TextField date;
    
    @FXML
    private TextField montant;

    @FXML
    private TextField qte;
    
    @FXML
    private TextField nomm;
    
    
    @FXML
    void InsererOnAction(ActionEvent event) {
        if ( !date.getText().isEmpty() && !nomm.getText().isEmpty() && !qte.getText().isEmpty() && !montant.getText().isEmpty()) {
	    try {
	    	String sql =  "UPDATE `gestiondeventes` SET `datev`= '"+date.getText()+"', `nommed`= '"+nomm.getText()+"', `qtev`= '"+qte.getText()+"', `montv`= '"+montant.getText()+"' WHERE `idv` = '"+id.getText()+"'" ;
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
        id.setText(GestionDeVenteController.selectedname);
    }
    
}