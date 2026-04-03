package edu.curso;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class HelloFx extends Application { 
    @Override
    public void start( Stage stage ) { 
        Pane painel = new Pane();
        Scene scn = new Scene( painel, 400, 200 );

        Label lblHello = new Label("Hello FX");
        lblHello.setStyle("-fx-font-size: 38; -fx-text-fill: 'red'; -fx-rotate: 0");
        lblHello.relocate( 100, 25 );

        TextField txtNome = new TextField();
        Button btnGravar = new Button("Gravar");

        painel.getChildren().addAll( lblHello, txtNome, btnGravar );

        stage.setScene( scn );
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch( HelloFx.class, args );
    }
}