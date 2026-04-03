package edu.curso;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class App extends Application { 

    @Override
    public void start(Stage stage) { 
        // VBox painel = new VBox();
        AnchorPane painel = new AnchorPane();
        Scene scn = new Scene( painel, 600, 200 );
        Label lblNome = new Label("Nome:");
        lblNome.setStyle("-fx-rotate: 45; -fx-text-fill: 'red'; -fx-font-size: 32");
        Button btnOk = new Button("Gravar");
        TextField txtNome = new TextField();

        painel.getChildren().addAll(lblNome, txtNome, btnOk);

        // BorderPane stkPane = new BorderPane();
        // stkPane.setCenter( btnOk );
        // BorderPane.setAlignment(btnOk, Pos.CENTER);
        
        AnchorPane.setTopAnchor(lblNome, 30.0);
        AnchorPane.setRightAnchor(btnOk, 20.0);
        AnchorPane.setBottomAnchor(txtNome, 10.0);
        
   
        stage.setScene( scn );
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}

