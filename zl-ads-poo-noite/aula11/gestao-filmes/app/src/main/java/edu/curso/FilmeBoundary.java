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

public class FilmeBoundary extends Application {

    private TextField txtTitulo = new TextField();
    private TextField txtGenero = new TextField();
    private TextField txtLancamento = new TextField();

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private FilmeControl control = new FilmeControl();

    public Filme toEntity() { 
        Filme f = new Filme();
        f.setTitulo(txtTitulo.getText());
        f.setGenero(txtGenero.getText());
        LocalDate data = LocalDate.parse( txtLancamento.getText(), dtf );
        f.setLancamento( data );

        return f;
    }

    public void toBoundary( Filme f ){ 
        if (f != null) {
            txtTitulo.setText( f.getTitulo() );
            txtGenero.setText( f.getGenero() );
            String strData = f.getLancamento().format( dtf );
            txtLancamento.setText( strData );
        }
    }
    
    @Override
    public void start( Stage stage) { 
        GridPane paneCampos = new GridPane();
        Scene scn = new Scene(paneCampos, 400, 300);

        // Label lblTitulo = new Label("Titulo");
        paneCampos.add( new Label("Titulo"), 0, 0 );
        paneCampos.add( txtTitulo, 1, 0);
        paneCampos.add( new Label("Genero"), 0, 1 );
        paneCampos.add( txtGenero, 1, 1);
        paneCampos.add( new Label("Lancamento"), 0, 2 );
        paneCampos.add( txtLancamento, 1, 2);

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction( (e) -> { 
            control.salvar( toEntity() );
            new Alert(AlertType.INFORMATION, "Filme gravado com sucesso").show();
            toBoundary( new Filme() );
        });
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction((e) -> {
            Filme f = control.pesquisar( txtTitulo.getText() );
            if (f != null) {
                toBoundary(f);
            } else { 
                new Alert(AlertType.INFORMATION, "Nenhum filme encontrado").show();
            }
        });

        paneCampos.add(btnSalvar, 0, 3);
        paneCampos.add(btnPesquisar, 1, 3);

        stage.setScene(scn);
        stage.show();
    }
}
