package edu.curso;

import java.time.LocalDate;

public class Pet {
    private long id;
    private String tipo = "";
    private String nome = "";
    private LocalDate nascimento = LocalDate.now();

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }
    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }  
}
