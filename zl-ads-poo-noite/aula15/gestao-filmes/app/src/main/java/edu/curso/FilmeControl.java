package edu.curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FilmeControl {
   
    private ObservableList<Filme> lista = FXCollections.observableArrayList();

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty titulo = new SimpleStringProperty("");
    private StringProperty genero = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> lancamento = new SimpleObjectProperty<>(LocalDate.now());

    private FilmeDAO dao = new FilmeDAOImplementation();

    public FilmeControl() { 
        carregar();
    }

    public void fromEntity( Filme f ) {
        if (f != null) {
            id.set( f.getId() );
            titulo.set( f.getTitulo() );
            genero.set( f.getGenero() );
            lancamento.set(f.getLancamento());
        }
    }

    public Filme toEntity() { 
        Filme f = new Filme();
        f.setId( id.get() );
        f.setTitulo( titulo.get() );
        f.setGenero( genero.get() );
        f.setLancamento( lancamento.get() );
        return f;
    }

    public void limparCampos() { 
        id.set(0);
        titulo.set("");
        genero.set("");
        lancamento.set(LocalDate.now());
    }

    public void salvar() {
        Filme f = toEntity();
        System.out.println("ID do Filme ==> " + f.getId());
        if (id.get() > 0) { 
            dao.atualizar(id.get(), f);
        } else { 
            dao.cadastrar( f );
        }
        limparCampos();
        carregar();
    }

    public void carregar() { 
        lista.clear();
        lista.addAll( 
            dao.pesquisarPorTitulo("")
        );
    }

    public void apagar( int indice ) { 
        Filme f = lista.get( indice );
        dao.apagar(f);
        carregar();
    }

    public void pesquisar() {
        lista.clear();
        lista.addAll( 
            dao.pesquisarPorTitulo( getTitulo() )
        );
    }

    public String getTitulo() { 
        return titulo.get();
    }

    public StringProperty tituloProperty() { 
        return titulo;
    }

    public StringProperty generoProperty() { 
        return genero;
    }

    public ObjectProperty<LocalDate> lancamentoProperty() { 
        return lancamento;
    }

    public ObservableList<Filme> getLista() { 
        return lista;
    }

}
