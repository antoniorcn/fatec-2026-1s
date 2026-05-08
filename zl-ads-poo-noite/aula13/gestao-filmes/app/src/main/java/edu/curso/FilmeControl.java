package edu.curso;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FilmeControl {
    
    private ObservableList<Filme> lista = FXCollections.observableArrayList();

    private StringProperty titulo = new SimpleStringProperty("");
    private StringProperty genero = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> lancamento = new SimpleObjectProperty<>(LocalDate.now());


    public void fromEntity( Filme f ) {
        if (f != null) {
            titulo.set( f.getTitulo() );
            genero.set( f.getGenero() );
            lancamento.set(f.getLancamento());
        }
    }

    public Filme toEntity() { 
        Filme f = new Filme();
        f.setTitulo( titulo.get() );
        f.setGenero( genero.get() );
        f.setLancamento( lancamento.get() );
        return f;
    }

    public void salvar() {
        lista.add( toEntity() );
    }

    public Filme pesquisar() {
        for ( Filme f : lista ) { 
            if (f.getTitulo().contains( titulo.get() )) { 
               fromEntity( f ); 
            }
        }
        return null;
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
