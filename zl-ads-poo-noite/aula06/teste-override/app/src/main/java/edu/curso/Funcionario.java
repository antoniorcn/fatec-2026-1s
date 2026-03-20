package edu.curso;

public class Funcionario {

    String nome;
    public Funcionario( String n ) { 
        this.nome = n;
    }

    
    public void receberPagamento( double valor ) { 
        System.out.println(nome + " recebeu " + valor);
    }
    
}
