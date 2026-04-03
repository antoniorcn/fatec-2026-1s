package edu.curso;

import javafx.application.Application;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloFx extends Application { 

    @Override
    public void start(Stage stage) { 
        Pane painel = new Pane();
        Scene scn = new Scene( painel, 400, 200 );

        Label lblNome = new Label("Nome:");
        Button btnOk = new Button("Gravar");
        TextField txtNome = new TextField();
        painel.getChildren().addAll( lblNome, btnOk, txtNome );
        lblNome.relocate(50, 100);
        btnOk.relocate(200, 150);
        txtNome.relocate( 100, 100 );
        // txtNome.prefWidth(150);
        // txtNome.setPrefWidth(300);
        txtNome.setPrefSize(250, 60);

        stage.setScene( scn );
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(HelloFx.class, args);
    }
}