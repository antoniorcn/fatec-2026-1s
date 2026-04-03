package edu.curso;

import javafx.application.Application;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TesteTilePane extends Application { 

    @Override
    public void start(Stage stage) { 
        TilePane painel = new TilePane();
        Scene scn = new Scene( painel, 600, 200 );

        Label lblNome = new Label("Nome:");
        Button btnOk = new Button("Gravar");
        TextField txtNome = new TextField();
        
        painel.getChildren().addAll(lblNome, txtNome, btnOk);
        painel.setPrefColumns(2);
    
        stage.setScene( scn );
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}

