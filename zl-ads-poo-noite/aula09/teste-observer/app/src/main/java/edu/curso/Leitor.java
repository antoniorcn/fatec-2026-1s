package edu.curso;

public class Leitor implements Assinante {

    private String nome = "";

    public Leitor( String nome ) { 
        this.nome = nome;
    }

    public void acao( String msg ) { 
        System.out.println(this.nome + " esta lendo " + msg );
    }
    
}
