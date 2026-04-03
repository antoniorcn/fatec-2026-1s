package edu.curso;

import javafx.application.Application;

import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TesteFlowPane extends Application { 

    @Override
    public void start(Stage stage) { 
        FlowPane painel = new FlowPane();
        painel.setHgap(20);
        Scene scn = new Scene( painel, 400, 200 );

        Label lblNome = new Label("Nome:");
        Button btnOk = new Button("Gravar");
        TextField txtNome = new TextField();
        painel.getChildren().addAll( lblNome, btnOk, txtNome );

        stage.setScene( scn );
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(TesteFlowPane.class, args);
    }
}

