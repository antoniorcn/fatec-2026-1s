package edu.curso;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class TesteGridPane extends Application { 
    @Override
    public void start( Stage stage ) { 
        GridPane painel = new GridPane();
        Scene scn = new Scene( painel, 400, 200 );

        Label lblHello = new Label("Hello FX");
        lblHello.setStyle("-fx-font-size: 38; -fx-text-fill: 'red'; -fx-rotate: 0");

        TextField txtNome = new TextField();
        Button btnGravar = new Button("Gravar");

        painel.add( lblHello, 0, 0 );
        painel.add( txtNome, 1, 0 ); 
        painel.add( btnGravar, 1, 1 );  
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(70);
        painel.getColumnConstraints().addAll( col1, col2 );
  

        stage.setScene( scn );
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch( App.class, args );
    }
}