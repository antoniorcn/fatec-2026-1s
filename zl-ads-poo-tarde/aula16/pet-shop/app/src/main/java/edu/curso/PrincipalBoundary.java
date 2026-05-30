package edu.curso;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PrincipalBoundary extends Application {
    private Pane petBoundary = new PetBoundary().render();
    private Pane vetBoundary = new VeterinarioBoundary().render();

    @Override
    public void start(Stage stage) {

        BorderPane panPrincipal = new BorderPane();

        Scene scn = new Scene(panPrincipal, 800, 600);

        MenuBar menuBar = new MenuBar();

        Menu mnuArquivo = new Menu("Arquivo");
        Menu mnuCadastro = new Menu("Cadastro");
        Menu mnuAjuda = new Menu("Ajuda");

        menuBar.getMenus().addAll( mnuArquivo, mnuCadastro, mnuAjuda );

        MenuItem mnuPetItem = new MenuItem("Pets");
        MenuItem mnuVetItem = new MenuItem("Veterinarios");

        mnuPetItem.setOnAction( e -> panPrincipal.setCenter( petBoundary ) );
        mnuVetItem.setOnAction( e -> panPrincipal.setCenter( vetBoundary) );

        mnuCadastro.getItems().addAll( mnuPetItem, mnuVetItem );

        panPrincipal.setTop( menuBar );
        // panPrincipal.setCenter( vetBoundary );

        stage.setScene(scn);
        stage.show();
    }
}
