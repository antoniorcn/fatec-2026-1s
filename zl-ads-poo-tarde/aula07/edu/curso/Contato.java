package edu.curso;

import java.io.Serializable;

public class Contato implements Serializable{ 
    String nome;
    String telefone;
    transient String email;

    public Contato( String nome, String telefone, String email) { 
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
}