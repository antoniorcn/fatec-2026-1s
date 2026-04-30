package edu.curso;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;

public class PetControl {

    private List<Pet> lista = new ArrayList<>();

    StringProperty nome = new SimpleStringProperty("");
    StringProperty tipo = new SimpleStringProperty("");
    ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>(LocalDate.now());

    // ChangeListener<String> observador = new ChangeListener<>() {
    //     public void changed(ObservableValue<? extends String> obj, String antigo, String novo) { 
    //         System.out.printf("Objeto alterado de %s para %s\n", antigo, novo);            
    //     } 
    // };

    public PetControl() { 
        System.out.println("Pet Control criado");
    }

    public void limparCampos() { 
        tipo.set("");
        nome.set("");
        nascimento.set(LocalDate.now());
    }

    public void salvar() { 
        Pet p = toEntity();
        lista.add( p );
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
    
}
