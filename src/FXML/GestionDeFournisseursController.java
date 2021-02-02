package FXML;


import MainApp.PhManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.stream.Collector;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;

public class GestionDeFournisseursController {
    ObservableList<ObservableList> data = FXCollections.observableArrayList();
    
    public static String selectedname="";

    @FXML
    private TextField telf;

    @FXML
    private TextField villef;

    @FXML
    private TextField idf;

    @FXML
    private TextField nomf;

    @FXML
    private TextField adf;
    
    @FXML
    private TextField rechf;

    @FXML
    private TableView<ObservableList> tableGestionDeFournisseurs;

    
    @FXML
    void HomeActionReturn4(ActionEvent event) {
        
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("HomeInterface.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);        
            ((Stage)((Node)event.getTarget()).getScene().getWindow()).setScene(scene);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    @FXML
    void LoginActionReturn(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LoginInterface.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);        
            ((Stage)((Node)event.getTarget()).getScene().getWindow()).setScene(scene);
        }catch(Exception e){
            System.out.println(e);
        }

    }
    
    public void initialize(){
        try {
            String sql =  "select * from gestiondefournisseurs where 1" ;
            Statement smt = PhManager.con.createStatement() ;
            ResultSet rs = smt.executeQuery(sql) ;
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory((Callback) new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableGestionDeFournisseurs.getColumns().addAll(col); 
            }
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row);

            }
            //FINALLY ADDED TO TableView
            tableGestionDeFournisseurs.setItems(data);

        }catch(Exception e ) {
            System.out.println(e);
        }
    }
    @FXML
    void AjouterOnAction(ActionEvent event) {
        try {
            String sql = "INSERT INTO gestiondefournisseurs(idf, nomfourn, adresse, numtel, ville) VALUES ('"+idf.getText()+"','"+nomf.getText()+"','"+adf.getText()+"','"+telf.getText()+"','"+villef.getText()+"')";
            Statement smt = PhManager.con.createStatement() ;
            smt.executeUpdate((sql)) ;
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    void SupprimerOnAction(ActionEvent event) {
        ObservableList<ObservableList> dataa = (ObservableList<ObservableList>) tableGestionDeFournisseurs.getSelectionModel().getSelectedItem();
        try {
            String sql =  "DELETE FROM gestiondefournisseurs WHERE idf = '"+(String)(Object)dataa.get(0)+"'";
            Statement smt = PhManager.con.createStatement() ;
            smt.executeUpdate((sql)) ;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Fournisseur "+(String)(Object)dataa.get(0)+" supprimé");
            Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertstage.getIcons().add(new Image("/Images/PhManager.png")); // To add an icon
            alert.showAndWait();
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    void ModifierOnAction(ActionEvent event) {
        try {
            ObservableList<ObservableList> dataa = tableGestionDeFournisseurs.getSelectionModel().getSelectedItem();
            selectedname =(String)(Object)dataa.get(0);
            Stage logp = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("ModifierGestionDeFournisseursInterface.fxml"));
            Scene scene = new Scene(root);
            logp.setScene(scene);
            logp.show();
            logp.setResizable(false);
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    @FXML
    void RechercherOnAction(ActionEvent event) {
        String vaal = rechf.getText().toString();
        ObservableList<ObservableList> dataa =data.stream().filter(cas -> cas.get(1).toString().contains(vaal)).collect(Collector.of(
                    FXCollections::observableArrayList,
                    ObservableList::add,
                    (l1, l2) -> { l1.addAll(l2); return l1; }));
            //FINALLY ADDED TO TableView
        tableGestionDeFournisseurs.setItems(dataa);
    }
}
   