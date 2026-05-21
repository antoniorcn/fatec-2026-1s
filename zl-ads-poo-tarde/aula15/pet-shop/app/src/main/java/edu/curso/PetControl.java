package edu.curso;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;

public class PetControl {
    private ObservableList<Pet> lista = FXCollections.observableArrayList();

    StringProperty nome = new SimpleStringProperty("");
    StringProperty tipo = new SimpleStringProperty("");
    ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>(LocalDate.now());

    private PetDAO dao = new PetDAOImpl();

    public PetControl() { 
        
    }

    public void limparCampos() { 
        tipo.set("");
        nome.set("");
        nascimento.set(LocalDate.now());
    }

    public void salvar() { 
        Pet p = toEntity();
        lista.add( p );
        dao.cadastrar(p);
    }

    public void carregar() { 
        lista.clear();
        lista.addAll(
            dao.consultarPorNome( nome.get() )
        );
    }
 
    public void pesquisar() { 
        for ( Pet p : lista ) { 
            if (p.getNome().contains( nome.get() )) { 
                toBoundary( p );
            }
        }
    }

    public Pet toEntity() { 
        Pet p = new Pet();
        p.setTipo( tipo.get() );
        p.setNome( nome.get() );
        p.setNascimento( nascimento.get() );
        return p;
    }

    public void toBoundary(Pet p) { 
        if (p != null) { 
            tipo.set(p.getTipo());
            nome.set(p.getNome());
            nascimento.set( p.getNascimento() );
        }
    }

    public ObservableList<Pet> getLista() { 
        return lista;
    }
    
}
