package edu.curso;

import java.util.ArrayList;
import java.util.List;

public class FilmeControl {
    
    private List<Filme> lista = new ArrayList<>();

    public void salvar( Filme filme ) { 
        lista.add( filme );
    }

    public Filme pesquisar( String titulo ) {
        for ( Filme f : lista ) { 
            if (f.getTitulo().contains( titulo )) { 
                return f;
            }
        }
        return null;
    }

}
