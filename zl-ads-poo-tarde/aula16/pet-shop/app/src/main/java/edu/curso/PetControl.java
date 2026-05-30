package edu.curso;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;

public class PetControl {
    private ObservableList<Pet> lista = FXCollections.observableArrayList();

    LongProperty id = new SimpleLongProperty( -1 );
    StringProperty nome = new SimpleStringProperty("");
    StringProperty tipo = new SimpleStringProperty("");
    ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>(LocalDate.now());

    private PetDAO dao = new PetDAOImpl();

    public PetControl() { 
        carregar();
    }

    public void limparCampos() {
        id.set(-1);
        tipo.set("");
        nome.set("");
        nascimento.set(LocalDate.now());
    }

    public void salvar() { 
        Pet p = toEntity();
        if (p.getId() > 0) {
            dao.atualizar( p.getId(), p );
        } else { 
            dao.cadastrar(p);
        }
        limparCampos();
        carregar();
    }

    public void carregar() { 
        lista.clear();
        lista.addAll(
            dao.consultarPorNome( "" )
        );
    }
 
    public void pesquisar() { 
        lista.clear();
        lista.addAll(
            dao.consultarPorNome( nome.get() )
        );
    }

    public void apagar( int index ){ 
        Pet p = lista.get( index );
        dao.apagar( p.getId() );
        carregar();
    }

    public Pet toEntity() { 
        Pet p = new Pet();
        p.setId( id.get() );
        p.setTipo( tipo.get() );
        p.setNome( nome.get() );
        p.setNascimento( nascimento.get() );
        return p;
    }

    public void toBoundary(Pet p) { 
        if (p != null) {
            id.set( p.getId() );
            tipo.set(p.getTipo());
            nome.set(p.getNome());
            nascimento.set( p.getNascimento() );
        }
    }

    public ObservableList<Pet> getLista() { 
        return lista;
    }
    
}
