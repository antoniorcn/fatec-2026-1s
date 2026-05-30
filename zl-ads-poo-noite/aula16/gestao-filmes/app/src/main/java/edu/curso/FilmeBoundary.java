package edu.curso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

public class FilmeBoundary implements Tela {
    private ObservableList<String> generos = 
        FXCollections.observableArrayList("Ação", "Terror", "Drama", "Comédia", "Documentario");
    private TextField txtTitulo = new TextField();
    private ComboBox<String> cmbGenero = new ComboBox<>();
    private DatePicker dtaLancamento = new DatePicker();

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private FilmeControl control = new FilmeControl();

    private TableView<Filme> table = new TableView<>();
    
    @Override
    public Pane render() { 

        BorderPane panPrincipal = new BorderPane();

        GridPane paneCampos = new GridPane();

        cmbGenero.setItems( generos );

        // Label lblTitulo = new Label("Titulo");
        paneCampos.add( new Label("Titulo"), 0, 0 );
        paneCampos.add( txtTitulo, 1, 0);
        paneCampos.add( new Label("Genero"), 0, 1 );
        paneCampos.add( cmbGenero, 1, 1);
        paneCampos.add( new Label("Lancamento"), 0, 2 );
        paneCampos.add( dtaLancamento, 1, 2);

        panPrincipal.setTop( paneCampos );
        panPrincipal.setCenter( table );

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction( (e) -> { 
            control.salvar();
            new Alert(AlertType.INFORMATION, "Filme gravado com sucesso").show();
        });
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction((e) -> {
            control.pesquisar();
        });

        paneCampos.add(btnSalvar, 0, 3);
        paneCampos.add(btnPesquisar, 1, 3);

        Image iconNew = new Image(getClass().getResourceAsStream("/images/borracha.png"));
        ImageView imgViewNew = new ImageView( iconNew );
        imgViewNew.setFitWidth(25);
        imgViewNew.setFitHeight(25);
        Button btnNovo = new Button();
        btnNovo.setGraphic( imgViewNew );
        btnNovo.setOnAction( e -> control.limparCampos());
       

        paneCampos.add( btnNovo, 4, 0);


        StringConverter<? extends LocalDate> converter = new LocalDateStringConverter();

        Bindings.bindBidirectional(txtTitulo.textProperty(), control.tituloProperty());
        Bindings.bindBidirectional(cmbGenero.valueProperty(), control.generoProperty());
        Bindings.bindBidirectional(dtaLancamento.valueProperty(), control.lancamentoProperty());


        // Criar as colunas da tabela
        TableColumn<Filme, String> colTitulo = new TableColumn<>("Titulo");
        colTitulo.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getTitulo())
        );

        TableColumn<Filme, String> colGenero = new TableColumn<>("Genero");
        colGenero.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper( itemData.getValue().getGenero() )
        );

        TableColumn<Filme, String> colLancamento = new TableColumn<>("Lancamento");
        colLancamento.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper( 
                itemData.getValue().getLancamento().format(dtf)
            )
        );

        TableColumn<Filme, Void> colAcoes = new TableColumn<>("Ações");
        
        table.getSelectionModel().selectedItemProperty().addListener(
            (obj, antigo, novo) -> control.fromEntity( novo )
        );

        table.getColumns().add( colTitulo );
        table.getColumns().add( colGenero );
        table.getColumns().add( colLancamento );
        table.getColumns().add( colAcoes );

        table.setItems( control.getLista() );

        Callback<TableColumn<Filme, Void>, TableCell<Filme, Void>> 
            callback = new Callback<>(){ 
                public TableCell<Filme, Void> call(TableColumn<Filme, Void> column) { 
                    return new TableCell<Filme, Void>(){
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
                                    control.apagar( getIndex() ) ;
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
