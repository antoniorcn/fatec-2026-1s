package edu.curso;

import javafx.application.Application;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TesteGridPane extends Application { 

    @Override
    public void start(Stage stage) { 
        GridPane painel = new GridPane();
        Scene scn = new Scene( painel, 600, 200 );

        Label lblNome = new Label("Nome:");
        Button btnOk = new Button("Gravar");
        TextField txtNome = new TextField();

        painel.setHgap(10);
        painel.setVgap(10);
        
        painel.add(lblNome, 0, 0);
        painel.add(txtNome, 1, 0);
        painel.add(btnOk, 0, 1);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(70);

        painel.getColumnConstraints().addAll( col1, col2 );
    
        stage.setScene( scn );
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}

