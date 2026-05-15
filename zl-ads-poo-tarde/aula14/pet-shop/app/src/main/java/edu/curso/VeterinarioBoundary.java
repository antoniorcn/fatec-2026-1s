package edu.curso;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VeterinarioBoundary extends Application {

    private TextField txtNome = new TextField();
    private TextField txtEspecialidade = new TextField();
    private TextField txtCRV = new TextField();

    private Button btnSalvar = new Button("Salvar");
    private Button btnPesquisar = new Button("Pesquisar");

    private VeterinarioControl control = new VeterinarioControl();

    private TableView<Veterinario> table = new TableView<>();

    public void start( Stage stage ) { 

        BorderPane panePrincipal = new BorderPane();

        GridPane pane = new GridPane();
        pane.add(new Label("Nome: "), 0, 0);
        pane.add(txtNome, 1, 0);
        pane.add(new Label("Especialidade: "), 0, 1);
        pane.add(txtEspecialidade, 1, 1);
        pane.add(new Label("CRV: "), 0, 2);
        pane.add(txtCRV, 1, 2);
        pane.add(btnSalvar, 0, 3);
        pane.add(btnPesquisar, 1, 3);

        panePrincipal.setTop( pane );
        panePrincipal.setCenter( table );

        Scene scn = new Scene( panePrincipal, 400, 300);

        btnSalvar.setOnAction( e -> control.salvar() );
        btnPesquisar.setOnAction( e -> control.pesquisarPorNome() );

        Bindings.bindBidirectional( txtNome.textProperty(), control.nomeProperty() );
        Bindings.bindBidirectional( txtEspecialidade.textProperty(), control.especialidadeProperty() );
        Bindings.bindBidirectional( txtCRV.textProperty(), control.crvProperty() );


        TableColumn<Veterinario, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome())
        );
        TableColumn<Veterinario, String> colEspecialidade = new TableColumn<>("Especialidade");
        colEspecialidade.setCellValueFactory( 
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getEspecialidade())
        );

        table.getColumns().add( colNome );
        table.getColumns().add( colEspecialidade );

        table.getSelectionModel().selectedItemProperty().addListener(
            ( obj, antigo, novo ) -> control.fromEntity( novo )
        );

        table.setItems( control.getLista() );

        stage.setScene( scn );

        stage.show();
    }
}
