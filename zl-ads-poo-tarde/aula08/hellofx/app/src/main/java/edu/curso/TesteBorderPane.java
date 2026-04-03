package edu.curso;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TesteBorderPane extends Application { 

    @Override
    public void start(Stage stage) { 
        BorderPane painel = new BorderPane();
        Scene scn = new Scene( painel, 400, 200 );

        Label lblNome = new Label("Nome:");
        Button btnOk = new Button("Gravar");
        TextField txtNome = new TextField();
        
        painel.setLeft( lblNome );
        BorderPane.setAlignment(lblNome, Pos.CENTER);
        painel.setCenter(txtNome);
        painel.setBottom(btnOk);

        stage.setScene( scn );
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}

