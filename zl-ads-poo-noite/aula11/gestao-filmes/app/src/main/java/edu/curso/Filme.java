package edu.curso;

import java.time.LocalDate;

public class Filme {
    private long id = 0;
    private String titulo = "";
    private String genero = "";
    private LocalDate lancamento = LocalDate.now();

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }
    public void setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
    }

    @Override
    public String toString() { 
        return this.genero + " - " + this.titulo;
    }

    @Override
    public boolean equals( Object f ) {
        if (f instanceof Filme filme) { 
            // Filme filme = (Filme)f;
            if (filme.getTitulo().equals(getTitulo()) && 
                filme.getLancamento().toEpochDay() == 
                    getLancamento().toEpochDay()) { 
                return true;
            }
        }
        return false;
    }
}
