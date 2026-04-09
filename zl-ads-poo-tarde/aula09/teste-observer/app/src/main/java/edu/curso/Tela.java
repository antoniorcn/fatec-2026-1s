package edu.curso;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

class ManipuladorEvento implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        System.out.println(String.format("Evento de Mouse Tipo: %s\tPOS(%4.1f, %4.1f) Button: %d", 
        event.getEventType().getName(),
        event.getX(), event.getY(), 
        event.getButton().ordinal()));
    } 
    
}

public class Tela extends Application {

    @Override
    public void start( Stage stage ) { 
        AnchorPane pane = new AnchorPane();
        
        Scene scn = new Scene( pane, 600, 400 );

        Label lblTitulo = new Label("Teste de Eventos");
        Button btnOk = new Button("Ok");

        pane.getChildren().addAll( lblTitulo, btnOk );

        AnchorPane.setTopAnchor(lblTitulo, 20.0);
        AnchorPane.setBottomAnchor(btnOk, 20.0);
        AnchorPane.setLeftAnchor(btnOk, 100.0);
        AnchorPane.setRightAnchor(btnOk, 100.0);
        AnchorPane.setLeftAnchor(lblTitulo, 20.0);
        AnchorPane.setRightAnchor(lblTitulo, 20.0);

        ManipuladorEvento man = new ManipuladorEvento();
        btnOk.addEventFilter(MouseEvent.MOUSE_CLICKED, man);

        stage.setScene(scn);
        stage.show();
    }
}
