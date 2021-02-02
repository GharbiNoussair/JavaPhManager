package MainApp;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author gharb
 */
public class PhManager extends Application {
    public static Connection con = Connexionbdd.Connect();
    @Override
    public void start(Stage stage) {
        try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/LoginInterface.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("PhManager - ENICarthage");
        stage.show();
    }catch(Exception e){
            System.out.println(e);
            }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
