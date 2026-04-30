package edu.curso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.binding.Bindings;

import javafx.util.converter.LocalDateStringConverter;
import javafx.util.StringConverter;
import java.time.LocalDate;

public class PetBoundary extends Application {

    private TextField txtTipo = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtNascimento = new TextField();

    private PetControl control = new PetControl();

    private StringProperty nome = new SimpleStringProperty("");

    // private DateTimeFormatter dtf = DateTimeFormatter
                                        // .ofPattern("dd/MM/yyyy");

    private StringConverter<? extends LocalDate> dateConverter = 
        new LocalDateStringConverter();


    // public void limparCampos() { 
    //     // toBoundary( new Pet() );
    // }

    @Override
    public void start(Stage stage) { 
        System.out.println("Pet Shop Boundary");
        GridPane paneCampos = new GridPane();
        Scene scn = new Scene(paneCampos, 300, 200);

        paneCampos.add( new Label("Tipo"), 0, 0);
        paneCampos.add( txtTipo, 1, 0);
        paneCampos.add( new Label("Nome"), 0, 1);
        paneCampos.add( txtNome, 1, 1);
        paneCampos.add( new Label("Nascimento"), 0, 2);
        paneCampos.add( txtNascimento, 1, 2);

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction( ( e ) -> {
            control.salvar();
            control.limparCampos();
            new Alert(AlertType.INFORMATION, 
                "Pet foi salvo com sucesso").show();
        });
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction( ( e )-> {
            control.pesquisar();
            // if (p == null) {
            //     new Alert(AlertType.INFORMATION, 
            //     "Nenhum pet foi encontrado").show();
            // }
            // toBoundary( p );
        });
        paneCampos.add( btnSalvar, 0, 3);
        paneCampos.add( btnPesquisar, 1, 3);

        // txtNome.textProperty().addListener( control.observador );
        // txtNome.textProperty().bind( control.nome );
        Bindings.bindBidirectional( txtNome.textProperty(), control.nome );
        Bindings.bindBidirectional( txtTipo.textProperty(), control.tipo );
        Bindings.bindBidirectional( txtNascimento.textProperty(), 
                control.nascimento, (StringConverter<LocalDate>) dateConverter );

        // nome.addListener( control.observador );


        // EventHandler<KeyEvent> handler = new EventHandler<>(){ 
        //     public void handle(KeyEvent e) { 
        //         // System.out.println(e.getCharacter());
        //         nome.set( txtNome.getText() );
        //     }
        // };

        // txtNome.setOnKeyPressed(handler);

        stage.setScene(scn);
        stage.show();

        control.limparCampos();
    }
}
