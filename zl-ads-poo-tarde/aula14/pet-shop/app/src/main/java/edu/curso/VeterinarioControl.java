package edu.curso;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VeterinarioControl {

    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty especialidade = new SimpleStringProperty("");
    private StringProperty crv = new SimpleStringProperty("");

    private ObservableList<Veterinario> lista = FXCollections.observableArrayList();


    public Veterinario toEntity() { 
        Veterinario v1 = new Veterinario();
        v1.setNome( nome.get() );
        v1.setEspecialidade( especialidade.get() );
        v1.setCrv( crv.get() );

        return v1;
    }

    public void fromEntity( Veterinario v ) {
        if ( v != null ) { 
            nome.set( v.getNome() );
            especialidade.set( v.getEspecialidade() );
            crv.set( v.getCrv() );
        }
    } 

    public void salvar() { 
        lista.add( toEntity() );
    }


    public void pesquisarPorNome() { 
        for (Veterinario v : lista) { 
            if ( v.getNome().contains( nome.get() )) {
                fromEntity( v );
                break;
            }
        }
    }

    public void pesquisarPorCrv() { 
        for (Veterinario v : lista) { 
            if ( v.getCrv().contains( crv.get() )) {
                fromEntity( v );
                break;
            }
        } 
    }

    public StringProperty nomeProperty() { 
        return nome;
    }

    public StringProperty especialidadeProperty() { 
        return especialidade;
    }

    public StringProperty crvProperty() { 
        return crv;
    }

    public ObservableList<Veterinario> getLista() { 
        return lista;
    }
    
}
