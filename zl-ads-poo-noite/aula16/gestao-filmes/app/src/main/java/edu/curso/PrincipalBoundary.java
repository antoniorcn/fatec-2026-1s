package edu.curso;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PrincipalBoundary extends Application {
    private BorderPane pane = new BorderPane();
    private Pane filmePane = new FilmeBoundary().render();
    private Pane cinemaPane = new CinemaBoundary().render();

    @Override
    public void start(Stage stage) { 
        Scene scn = new Scene(pane, 800, 600);
        pane.setCenter( cinemaPane );

        MenuBar menuBar = new MenuBar();

        Menu mnuArquivo = new Menu("Arquivo");
        Menu mnuCadastro = new Menu("Cadastro");
        Menu mnuAjuda = new Menu("Ajuda");

        MenuItem mnuCinemaItem = new MenuItem("Cinemas");
        MenuItem mnuFilmeItem = new MenuItem("Filmes");

        menuBar.getMenus().addAll( mnuArquivo, mnuCadastro, mnuAjuda);

        mnuCadastro.getItems().addAll( mnuCinemaItem, mnuFilmeItem );

        pane.setTop( menuBar );


        mnuCinemaItem.setOnAction( e -> pane.setCenter( cinemaPane ) );
        mnuFilmeItem.setOnAction( e -> pane.setCenter( filmePane ) );

        stage.setScene(scn);
        stage.setTitle("Filmoteca");
        stage.show();
    }
    
}
