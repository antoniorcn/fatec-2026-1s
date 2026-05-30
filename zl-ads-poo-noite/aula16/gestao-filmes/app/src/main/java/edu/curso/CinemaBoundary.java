package edu.curso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

public class CinemaBoundary implements Tela {

    private TextField txtFranquia = new TextField();
    private TextField txtQuantidadeSalas = new TextField();
    private TextField txtEndereco = new TextField();
    private TextField txtCidade = new TextField();
    private TextField txtEstado = new TextField();


    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // private CinemaControl control = new CinemaControl();

    private TableView<Cinema> table = new TableView<>();
    
    @Override
    public Pane render() { 

        BorderPane panPrincipal = new BorderPane();

        GridPane paneCampos = new GridPane();

        // Label lblTitulo = new Label("Titulo");
        paneCampos.add( new Label("Franquia"), 0, 0 );
        paneCampos.add( txtFranquia, 1, 0);
        paneCampos.add( new Label("Quantidade Salas"), 0, 1 );
        paneCampos.add( txtQuantidadeSalas, 1, 1);
        paneCampos.add( new Label("Endereco"), 0, 2 );
        paneCampos.add( txtEndereco, 1, 2);
        paneCampos.add( new Label("Cidade"), 0, 3 );
        paneCampos.add( txtCidade, 1, 3);
        paneCampos.add( new Label("Estado"), 0, 4 );
        paneCampos.add( txtEstado, 1, 4);

        panPrincipal.setTop( paneCampos );
        panPrincipal.setCenter( table );

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction( (e) -> { 
            // control.salvar();
            new Alert(AlertType.INFORMATION, "Cinema gravado com sucesso").show();
        });
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction((e) -> {
            // control.pesquisar();
        });

        paneCampos.add(btnSalvar, 0, 5);
        paneCampos.add(btnPesquisar, 1, 5);

        Image iconNew = new Image(getClass().getResourceAsStream("/images/borracha.png"));
        ImageView imgViewNew = new ImageView( iconNew );
        imgViewNew.setFitWidth(25);
        imgViewNew.setFitHeight(25);
        Button btnNovo = new Button();
        btnNovo.setGraphic( imgViewNew );
        // btnNovo.setOnAction( e -> control.limparCampos());
       

        paneCampos.add( btnNovo, 4, 0);


        StringConverter<? extends LocalDate> converter = new LocalDateStringConverter();

        // Bindings.bindBidirectional(txtTitulo.textProperty(), control.tituloProperty());
        // Bindings.bindBidirectional(txtGenero.textProperty(), control.generoProperty());
        // Bindings.bindBidirectional(txtLancamento.textProperty(), control.lancamentoProperty(),
        //      (StringConverter<LocalDate>) converter);


        // Criar as colunas da tabela
        TableColumn<Cinema, String> colTitulo = new TableColumn<>("Franquia");
        colTitulo.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getFranquia())
        );

        TableColumn<Cinema, String> colGenero = new TableColumn<>("#Salas");
        colGenero.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper( String.valueOf(itemData.getValue().getQtdSalas()) )
        );

        TableColumn<Cinema, Void> colAcoes = new TableColumn<>("Ações");
        
        table.getSelectionModel().selectedItemProperty().addListener(
            (obj, antigo, novo) -> {}  // control.fromEntity( novo )
        );

        table.getColumns().add( colTitulo );
        table.getColumns().add( colGenero );
        table.getColumns().add( colAcoes );

        // table.setItems( control.getLista() );

        Callback<TableColumn<Cinema, Void>, TableCell<Cinema, Void>> 
            callback = new Callback<>(){ 
                public TableCell<Cinema, Void> call(TableColumn<Cinema, Void> column) { 
                    return new TableCell<Cinema, Void>(){
                        Button btnApagar = new Button();

                        {
                            Image iconDelete = new Image(getClass().getResourceAsStream("/images/delete.png"));
                            ImageView imgViewDelete = new ImageView( iconDelete );
                            imgViewDelete.setFitWidth(25);
                            imgViewDelete.setFitHeight(25);
                            btnApagar.setGraphic(imgViewDelete);
                            btnApagar.setOnAction(e -> {
                                Alert alert = new Alert(AlertType.CONFIRMATION, 
                                    "Apagar este Filme ?", ButtonType.YES, ButtonType.NO);
                                alert.setTitle("Confirma Deleção");

                                // 2. Show the alert and wait for a response
                                Optional<ButtonType> result = alert.showAndWait();

                                // 3. Handle the user's choice
                                if (result.isPresent() && result.get() == ButtonType.YES) {
                                    // control.apagar( getIndex() ) ;
                                }
                                
                            });
                        }
                        
                        public void updateItem(Void parm, boolean empty) {
                            
                            if (!empty) {
                                setGraphic( btnApagar );
                            } else {
                                setGraphic( null );
                            }
                        }
                    };
                }
        };

        colAcoes.setCellFactory( callback );
        return panPrincipal;
    }
}
