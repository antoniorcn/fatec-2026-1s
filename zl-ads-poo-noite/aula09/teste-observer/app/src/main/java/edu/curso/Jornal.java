package edu.curso;

import java.util.ArrayList;
import java.util.List;

public class Jornal implements Publicador {

    private String nome = "";
    private List<Assinante> lista = new ArrayList<>();

    public Jornal( String nome ) {
        this.nome = nome;
    }

    @Override
    public void adicionar(Assinante a) { 
        lista.add( a );
    }

    @Override
    public void remover(Assinante a) { 
        lista.remove( a );
    }

    @Override
    public void publicar(String msg) {
        System.out.println("Jornal " + this.nome + " acaba de publicar " + msg);
        for (Assinante a : lista) { 
            a.acao( msg );
        }
    }
    
}
