package edu.curso;

public class Leitor implements Assinante {

    private String nome = "";

    public Leitor( String nome ) { 
        this.nome = nome;
    }

    @Override
    public void notificado( String msg ) { 
        System.out.println("Eu "+ this.nome + " recebi a seguinte informação " + msg );
    }

}
