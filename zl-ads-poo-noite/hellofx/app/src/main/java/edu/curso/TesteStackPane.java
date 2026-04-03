package edu.curso;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class TesteStackPane extends Application { 
    @Override
    public void start( Stage stage ) { 
        StackPane painel = new StackPane();
        Scene scn = new Scene( painel, 400, 200 );

        Label lblHello = new Label("Hello FX");
        lblHello.setStyle("-fx-font-size: 38; -fx-text-fill: 'red'; -fx-rotate: 0");

        TextField txtNome = new TextField();
        Button btnGravar = new Button("Gravar");

        painel.getChildren().addAll( lblHello, txtNome, btnGravar );

        StackPane.setAlignment(lblHello, Pos.TOP_LEFT);
        StackPane.setAlignment(txtNome, Pos.CENTER);
        StackPane.setAlignment(btnGravar, Pos.BOTTOM_RIGHT);

        stage.setScene( scn );
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch( App.class, args );
    }
}