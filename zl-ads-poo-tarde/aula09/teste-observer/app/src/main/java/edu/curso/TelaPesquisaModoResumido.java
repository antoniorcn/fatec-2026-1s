package edu.curso;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;




public class TelaPesquisaModoResumido extends Application {
    private Label lblTitulo;
    private Label lblSubTitulo;
    private Button btnSalvar;
    private Button btnPesquisar;

    @Override
    public void start( Stage stage ) { 
        AnchorPane pane = new AnchorPane();
        
        Scene scn = new Scene( pane, 600, 400 );

        lblTitulo = new Label("Titulo");
        lblTitulo.setStyle("-fx-font-size: 32; -fx-alignment: 'center'");
        lblSubTitulo = new Label("SubTitulo");
        lblSubTitulo.setStyle("-fx-font-size: 28; -fx-alignment: 'center'");
        btnSalvar = new Button("Salvar");
        btnPesquisar = new Button("Pesquisar");

        pane.getChildren().addAll( lblTitulo, lblSubTitulo, btnSalvar, btnPesquisar );

        AnchorPane.setTopAnchor(lblTitulo, 20.0);
        AnchorPane.setLeftAnchor(lblTitulo, 20.0);
        AnchorPane.setRightAnchor(lblTitulo, 20.0);

        AnchorPane.setTopAnchor(lblSubTitulo, 100.0);
        AnchorPane.setLeftAnchor(lblSubTitulo, 20.0);
        AnchorPane.setRightAnchor(lblSubTitulo, 20.0);

        AnchorPane.setBottomAnchor(btnSalvar, 20.0);
        AnchorPane.setLeftAnchor(btnSalvar, 150.0);

        AnchorPane.setBottomAnchor(btnPesquisar, 20.0);
        AnchorPane.setRightAnchor(btnPesquisar, 150.0);        

        // scn.addEventFilter(ActionEvent.ANY, event -> { 
        //     if (event.getTarget() == btnSalvar) { 
        //         System.out.println("Salvando ...");
        //     } else if (event.getTarget() == btnPesquisar) { 
        //         System.out.println("Pesquisando ...");
        //     }
        // });

        // scn.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> { 
        //     if (event.getTarget() == lblTitulo) { 
        //         System.out.println("Clicado no Titulo");
        //     } else if (event.getTarget() == lblSubTitulo) { 
        //         System.out.println("Clicado no Sub Titulo");
        //     }
        // });

        lblTitulo.setOnMouseClicked(event -> System.out.println("Clicado no Titulo"));
        lblSubTitulo.setOnMouseClicked(event -> System.out.println("Clicado no Sub Titulo"));

        btnSalvar.setOnAction( event -> System.out.println("Salvando ...") );
        btnPesquisar.setOnAction( event -> System.out.println("Pesquisando ...") );

        stage.setScene(scn);
        stage.show();
    }
}
