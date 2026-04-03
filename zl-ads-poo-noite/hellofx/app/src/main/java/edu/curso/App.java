package edu.curso;

import javafx.application.Application;
import javafx.geometry.Insets;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class App extends Application { 
    @Override
    public void start( Stage stage ) { 
        HBox painel = new HBox();
        painel.setSpacing(20);
        Scene scn = new Scene( painel, 400, 200 );

        

        VBox painelOpcoes = new VBox();
        Label lblOpcao1 = new Label("Opcao 1");
        Label lblOpcao2 = new Label("Opcao 2");
        painelOpcoes.getChildren().addAll(lblOpcao1, lblOpcao2);
        painelOpcoes.setStyle("-fx-background-color: 'yellow'");

        HBox.setMargin(painelOpcoes, new Insets( 30.0 ));

        GridPane painelForm = new GridPane();
        painelForm.setStyle("-fx-background-color: 'cyan'");
        Label lblNome = new Label("Nome");
        TextField txtNome = new TextField();
        Button btnGravar = new Button("Gravar");

        painelForm.add( lblNome, 0, 0 );
        painelForm.add( txtNome, 1, 0 ); 
        painelForm.add( btnGravar, 1, 1 );  

        painel.getChildren().addAll( painelOpcoes, painelForm );
 

        stage.setScene( scn );
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch( App.class, args );
    }
}