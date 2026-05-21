package edu.curso;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;

import javafx.util.converter.LocalDateStringConverter;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.beans.property.ReadOnlyStringWrapper;

public class PetBoundary extends Application {

    private TextField txtTipo = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtNascimento = new TextField();

    private PetControl control = new PetControl();

    private TableView<Pet> table = new TableView<>();

    private StringConverter<? extends LocalDate> dateConverter = 
        new LocalDateStringConverter();

    @Override
    public void start(Stage stage) { 
        System.out.println("Pet Shop Boundary");
        BorderPane bp = new BorderPane();
        GridPane paneCampos = new GridPane();
        Scene scn = new Scene(bp, 300, 200);
        bp.setTop(paneCampos);
        bp.setCenter(table);
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
        });
        paneCampos.add( btnSalvar, 0, 3);
        paneCampos.add( btnPesquisar, 1, 3);

        Button btnLimparCampos = new Button();
        Image icon = new Image(getClass().getResourceAsStream("/images/new.png"));
        // 2. Wrap it in an ImageView
        ImageView imageView = new ImageView(icon);
        // 3. Optional: Resize the image to fit the button
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        // 4. Create the button and set the graphic
        btnLimparCampos.setGraphic(imageView);

        paneCampos.add( btnLimparCampos, 4, 0);
        btnLimparCampos.setOnAction( e -> control.limparCampos() );

        Bindings.bindBidirectional( txtNome.textProperty(), control.nome );
        Bindings.bindBidirectional( txtTipo.textProperty(), control.tipo );
        Bindings.bindBidirectional( txtNascimento.textProperty(), 
                control.nascimento, (StringConverter<LocalDate>) dateConverter );

        TableColumn<Pet, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory( 
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome())
        );
        TableColumn<Pet, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory( 
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getTipo())
        );
        TableColumn<Pet, String> colNascimento = new TableColumn<>("Nascimento");
        colNascimento.setCellValueFactory( 
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNascimento().toString())
        );

        TableColumn<Pet, Void> colAcoes = new TableColumn<>("Ações");
        Callback<TableColumn<Pet, Void>, TableCell<Pet, Void>> callBack = new Callback<>() {
            public TableCell<Pet, Void> call(TableColumn<Pet, Void> param) { 
                return new TableCell<Pet, Void>(){
                    private Button btnDelete = new Button("");
                    {
                        Image iconDelete = new Image(getClass().getResourceAsStream("/images/delete.png"));
                        // 2. Wrap it in an ImageView
                        ImageView deleteImageView = new ImageView(iconDelete);
                        // 3. Optional: Resize the image to fit the button
                        deleteImageView.setFitHeight(20);
                        deleteImageView.setFitWidth(20);
                        btnDelete.setGraphic(deleteImageView);

                        btnDelete.setOnAction( e -> control.apagar( getIndex() ));
                    }

                    public void updateItem(Void item, boolean empty) {
                        if (!empty) {
                            setGraphic( btnDelete );
                        } else { 
                            setGraphic( null );
                        }
                    }
                };
            }
        };
        colAcoes.setCellFactory( callBack );


        table.setItems( control.getLista() );

        table.getColumns().add( colNome );
        table.getColumns().add( colTipo );
        table.getColumns().add( colNascimento );
        table.getColumns().add( colAcoes );

        table.getSelectionModel().selectedItemProperty().addListener(
            (obj, antigo, nova) -> control.toBoundary(nova)
        );

        stage.setScene(scn);
        stage.show();

        control.limparCampos();
    }
}
