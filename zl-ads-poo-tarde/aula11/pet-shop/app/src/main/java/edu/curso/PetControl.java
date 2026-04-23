package edu.curso;

import java.util.ArrayList;
import java.util.List;

public class PetControl {

    private List<Pet> lista = new ArrayList<>();

    public void salvar( Pet p ) { 
        lista.add( p );
    }
 
    public Pet pesquisar( String nome ) { 
        for ( Pet p : lista ) { 
            if (p.getNome().contains( nome )) { 
                return p;
            }
        }
        return null;
    }
    
}
