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

public class PetBoundary extends Application {

    private TextField txtTipo = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtNascimento = new TextField();

    private PetControl control = new PetControl();

    private DateTimeFormatter dtf = DateTimeFormatter
                                        .ofPattern("dd/MM/yyyy");

    public Pet toEntity() { 
        Pet p = new Pet();
        p.setTipo( txtTipo.getText() );
        p.setNome( txtNome.getText() );
        LocalDate data = LocalDate.parse( txtNascimento.getText(), dtf );
        p.setNascimento( data );
        return p;
    }

    public void toBoundary(Pet p) { 
        if (p != null) { 
            txtTipo.setText(p.getTipo());
            txtNome.setText(p.getNome());
            String strData = p.getNascimento().format(dtf);
            txtNascimento.setText( strData );
        }
    }

    public void limparCampos() { 
        toBoundary( new Pet() );
    }

    @Override
    public void start(Stage stage) { 
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
            control.salvar( toEntity() );
            limparCampos();
            new Alert(AlertType.INFORMATION, 
                "Pet foi salvo com sucesso").show();
        });
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction( ( e )-> {
            Pet p = control.pesquisar( txtNome.getText() );
            if (p == null) {
                new Alert(AlertType.INFORMATION, 
                "Nenhum pet foi encontrado").show();
            }
            toBoundary( p );
        });
        paneCampos.add( btnSalvar, 0, 3);
        paneCampos.add( btnPesquisar, 1, 3);

        stage.setScene(scn);
        stage.show();

        limparCampos();
    }
}
